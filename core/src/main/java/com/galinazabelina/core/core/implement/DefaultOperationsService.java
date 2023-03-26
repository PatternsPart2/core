package com.galinazabelina.core.core.implement;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.galinazabelina.core.core.OperationsService;
import com.galinazabelina.core.core.repository.CreditAccountRepository;
import com.galinazabelina.core.core.repository.DebitAccountRepository;
import com.galinazabelina.core.model.AccountType;
import com.galinazabelina.core.model.OperationType;
import com.galinazabelina.core.model.entity.Account;
import com.galinazabelina.core.model.entity.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Service
@RequiredArgsConstructor
public class DefaultOperationsService implements OperationsService {

    private final DebitAccountRepository debitAccountRepository;

    private final CreditAccountRepository creditAccountRepository;

    @Autowired
    AmqpTemplate template;

    @Override
    public void changeBalance(Long accountId, BigDecimal changeValue, AccountType accountType) throws JsonProcessingException {
        if (accountType == AccountType.DEBIT
                && validateBalance(debitAccountRepository.getBalance(accountId).orElseThrow(), changeValue)) {
            debitAccountRepository.changeBalance(accountId, changeValue);
        } else if (accountType == AccountType.CREDIT
                && validateBalance(creditAccountRepository.getBalance(accountId).orElseThrow(), changeValue)) {
            creditAccountRepository.changeBalance(accountId, changeValue);
        }

        Account account = debitAccountRepository.findById(accountId)
                .orElse(creditAccountRepository.findById(accountId).orElseThrow());

        Operation operation = new Operation();
        operation.setAccountId(accountId);
        operation.setAmountOfMoney(changeValue.abs());
        operation.setCurrencyCode(account.getCurrencyCode());
        operation.setTimestamp(ZonedDateTime.now());
        operation.setOperationType(changeValue.signum() == 1 ? OperationType.REPLENISHMENT : OperationType.WITHDRAW);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        String operationString = objectMapper.writeValueAsString(operation);
        template.convertAndSend("operationQueue", operationString);
    }

    private boolean validateBalance(BigDecimal currentBalance, BigDecimal changeBalance) {
        return currentBalance.add(changeBalance).signum() == 1;
    }

}
