package com.galinazabelina.core.core.implement;

import com.galinazabelina.core.api.dto.AccountDto;
import com.galinazabelina.core.core.AccountService;
import com.galinazabelina.core.core.repository.CreditAccountRepository;
import com.galinazabelina.core.core.repository.DebitAccountRepository;
import com.galinazabelina.core.core.repository.UserRepository;
import com.galinazabelina.core.model.AccountType;
import com.galinazabelina.core.model.entity.Account;
import java.util.ArrayList;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DefaultAccountService implements AccountService {

    private final DebitAccountRepository debitAccountRepository;

    private final CreditAccountRepository creditAccountRepository;

    private final UserRepository userRepository;

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
        creditAccountRepository.findAll().forEach(accounts::add);
        return accounts.stream().map(this::entityToDto).toList();
    }

    @Override
    public AccountDto openAccount(AccountDto accountDto, AccountType accountType) {
        Account newAccount;
        if (accountType == AccountType.DEBIT) {
            newAccount = debitAccountRepository.save(dtoToAccount(accountDto));
        } else {
            newAccount = creditAccountRepository.save(dtoToAccount(accountDto));
        }
        return entityToDto(newAccount);
    }

    @Override
    public void closeAccount(Long id, AccountType accountType) {
        if (accountType == AccountType.DEBIT) {
            debitAccountRepository.closeAccountById(id);
        } else {
            creditAccountRepository.closeAccountById(id);
        }

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

    //todo add exceptions
    private Account dtoToAccount(AccountDto dto) {
        Account account = new Account();
        account.setId(dto.getId());
        account.setCurrencyCode(dto.getCurrencyCode());
        account.setBalance(dto.getBalance());
        account.setUser(userRepository.findById(dto.getUser().getId()).orElseThrow());
        account.setIsClosed(false);
        return account;
    }


}
