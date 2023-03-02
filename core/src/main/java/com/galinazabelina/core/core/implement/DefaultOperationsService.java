package com.galinazabelina.core.core.implement;

import com.galinazabelina.core.core.OperationsService;
import com.galinazabelina.core.core.repository.CreditAccountRepository;
import com.galinazabelina.core.core.repository.DebitAccountRepository;
import com.galinazabelina.core.model.AccountType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DefaultOperationsService implements OperationsService {

    private final DebitAccountRepository debitAccountRepository;

    private final CreditAccountRepository creditAccountRepository;

    @Override
    public void changeBalance(Long accountId, Long changeValue, AccountType accountType) {
        if (accountType == AccountType.DEBIT
                && validateBalance(debitAccountRepository.getBalance(accountId).orElseThrow(), changeValue)) {
            debitAccountRepository.changeBalance(accountId, changeValue);
        } else if (accountType == AccountType.CREDIT
                && validateBalance(creditAccountRepository.getBalance(accountId).orElseThrow(), changeValue)) {
            creditAccountRepository.changeBalance(accountId, changeValue);
        }
    }

    private boolean validateBalance(Long currentBalance, Long changeBalance) {
        return currentBalance + changeBalance < 0;
    }

}
