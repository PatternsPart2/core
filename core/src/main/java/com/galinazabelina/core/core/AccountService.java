package com.galinazabelina.core.core;

import com.galinazabelina.core.api.dto.AccountDto;
import com.galinazabelina.core.model.AccountType;
import com.galinazabelina.core.model.entity.Account;
import java.util.List;

public interface AccountService {

    AccountDto get (Long id);

    List<AccountDto> getAccounts();

    AccountDto openAccount(AccountDto accountDto, AccountType accountType);

    void closeAccount(Long id, AccountType accountType);

}
