package com.galinazabelina.core.core;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.galinazabelina.core.api.dto.AccountDto;
import com.galinazabelina.core.model.AccountType;
import java.util.List;

public interface AccountService {

    AccountDto get (Long id);

    List<AccountDto> getAccounts();

    AccountDto openAccount(AccountDto accountDto, AccountType accountType) throws JsonProcessingException;

    void closeAccount(Long id, AccountType accountType) throws JsonProcessingException;

}
