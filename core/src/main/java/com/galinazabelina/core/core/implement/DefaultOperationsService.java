package com.galinazabelina.core.core.implement;

import com.galinazabelina.core.core.OperationsService;
import com.galinazabelina.core.core.repository.CreditAccountRepository;
import com.galinazabelina.core.core.repository.DebitAccountRepository;
import com.galinazabelina.core.core.repository.OperationsHistoryRepository;
import com.galinazabelina.core.model.AccountType;
import com.galinazabelina.core.model.OperationType;
import com.galinazabelina.core.model.entity.Account;
import com.galinazabelina.core.model.entity.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;

import static java.lang.Math.abs;

@Service
@RequiredArgsConstructor
public class DefaultOperationsService implements OperationsService {

    private final DebitAccountRepository debitAccountRepository;

    private final CreditAccountRepository creditAccountRepository;

    private final OperationsHistoryRepository operationsHistoryRepository;

    @Override
    public void changeBalance(Long accountId, Long changeValue, AccountType accountType) {
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
        operation.setAmountOfMoney(abs(changeValue));
        operation.setCurrencyCode(account.getCurrencyCode());
        operation.setTimestamp(ZonedDateTime.now());
        operation.setOperationType(changeValue > 0 ? OperationType.REPLENISHMENT : OperationType.WITHDRAW);
        operationsHistoryRepository.save(operation);
    }

    private boolean validateBalance(Long currentBalance, Long changeBalance) {
        return currentBalance + changeBalance < 0;
    }

}
