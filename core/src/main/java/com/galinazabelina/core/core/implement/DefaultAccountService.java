package com.galinazabelina.core.core.implement;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.galinazabelina.core.api.dto.AccountDto;
import com.galinazabelina.core.core.AccountService;
import com.galinazabelina.core.core.repository.CreditAccountRepository;
import com.galinazabelina.core.core.repository.DebitAccountRepository;
import com.galinazabelina.core.core.repository.UserRepository;
import com.galinazabelina.core.model.AccountType;
import com.galinazabelina.core.model.OperationType;
import com.galinazabelina.core.model.entity.Account;

import java.time.ZonedDateTime;
import java.util.ArrayList;

import com.galinazabelina.core.model.entity.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DefaultAccountService implements AccountService {

    private final DebitAccountRepository debitAccountRepository;

    private final CreditAccountRepository creditAccountRepository;

    private final UserRepository userRepository;

    @Autowired
    AmqpTemplate template;

    @Override
    public AccountDto get(Long id) {
        Account account = debitAccountRepository.findById(id)
                .orElse(creditAccountRepository.findById(id).orElseThrow());
        return entityToDto(account);
    }

    @Override
    public List<AccountDto> getAccounts() {
        List<Account> accounts = new ArrayList<>();
        debitAccountRepository.findAll().forEach(accounts::add);
//        creditAccountRepository.findAll().forEach(accounts::add);
        return accounts.stream().map(this::entityToDto).toList();
    }

    @Override
    public AccountDto openAccount(AccountDto accountDto, AccountType accountType) throws JsonProcessingException {
        accountDto.setIsClosed(false);
        Account newAccount;
        if (accountType == AccountType.DEBIT) {
            newAccount = debitAccountRepository.save(dtoToAccount(accountDto, accountType));
        } else {
            newAccount = creditAccountRepository.save(dtoToAccount(accountDto, accountType));
        }
        Operation openOperation = new Operation();
        openOperation.setAccountId(newAccount.getId());
        openOperation.setTimestamp(ZonedDateTime.now());
        openOperation.setOperationType(OperationType.OPEN_ACCOUNT);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        String openOperationString = objectMapper.writeValueAsString(openOperation);
        template.convertAndSend("operationQueue", openOperationString);

        Operation replenishmentOperation = new Operation();
        replenishmentOperation.setAccountId(newAccount.getId());
        replenishmentOperation.setTimestamp(ZonedDateTime.now());
        replenishmentOperation.setOperationType(OperationType.REPLENISHMENT);
        replenishmentOperation.setAmountOfMoney(newAccount.getBalance());
        replenishmentOperation.setCurrencyCode(newAccount.getCurrencyCode());
        String replenishmentOperationString = objectMapper.writeValueAsString(openOperation);
        template.convertAndSend("operationQueue", replenishmentOperationString);
        return entityToDto(newAccount);
    }

    @Override
    public void closeAccount(Long id, AccountType accountType) throws JsonProcessingException {
        if (accountType == AccountType.DEBIT) {
            debitAccountRepository.closeAccountById(id);
        } else {
            creditAccountRepository.closeAccountById(id);
        }
        Operation operation = new Operation();
        operation.setAccountId(id);
        operation.setTimestamp(ZonedDateTime.now());
        operation.setOperationType(OperationType.CLOSE_ACCOUNT);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        String operationString = objectMapper.writeValueAsString(operation);
        template.convertAndSend("operationQueue", operationString);

    }

    private AccountDto entityToDto(Account account) {
        return new AccountDto(
                account.getId(),
                account.getUser(),
                account.getCurrencyCode(),
                account.getBalance(),
                account.getIsClosed(),
                account.getAccountType()
        );
    }

    private Account dtoToAccount(AccountDto dto, AccountType accountType) {
        Account account = new Account();
        account.setId(dto.getId());
        account.setCurrencyCode(dto.getCurrencyCode());
        account.setBalance(dto.getBalance());
        account.setUser(userRepository.findById(dto.getUser().getId()).orElse(null));
        account.setAccountType(accountType);
        account.setIsClosed(false);
        return account;
    }


}
