package com.galinazabelina.core.api;

import com.galinazabelina.core.api.dto.AccountDto;
import com.galinazabelina.core.core.AccountService;
import com.galinazabelina.core.core.Parameters;
import com.galinazabelina.core.model.AccountType;
import com.galinazabelina.core.model.entity.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;

import javax.ws.rs.*;
import java.util.List;

@Path(Paths.ACCOUNTS)
public class AccountResource {

    private final AccountService accountService;
    @Autowired
    public AccountResource(AccountService accountService) {
        this.accountService = accountService;
    }

    @GET
    public AccountDto getAccount(@PathParam(Parameters.id) Long id) {
        return accountService.get(id);
    }

    @GET
    public List<AccountDto> getAccounts() {
        return accountService.getAccounts();
    }

    @POST
    @Path(Paths.OPEN_DEBIT_ACCOUNT)
    public AccountDto openDebitAccount(@RequestParam(Parameters.accountDto) AccountDto accountDto) {
        return accountService.openAccount(accountDto, AccountType.DEBIT);
    }

    @POST
    @Path(Paths.OPEN_CREDIT_ACCOUNT)
    public AccountDto openCreditAccount(@RequestParam(Parameters.accountDto) AccountDto accountDto) {
        return accountService.openAccount(accountDto, AccountType.CREDIT);
    }

    @POST
    @Path(Paths.OPEN_DEBIT_ACCOUNT)
    public void closeDebitAccount(@FormParam(Parameters.id) Long id) {
        accountService.closeAccount(id, AccountType.DEBIT);
    }

    @POST
    @Path(Paths.CLOSE_CREDIT_ACCOUNT)
    public void closeCreditAccount(@FormParam(Parameters.id) Long id) {
        accountService.closeAccount(id, AccountType.CREDIT);
    }
}
