package com.galinazabelina.core.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.galinazabelina.core.api.dto.AccountDto;
import com.galinazabelina.core.core.AccountService;
import com.galinazabelina.core.core.Parameters;
import com.galinazabelina.core.model.AccountType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.*;
import java.util.List;

@RestController
@RequestMapping(Paths.ACCOUNTS)
public class AccountResource {

    private final AccountService accountService;
    @Autowired
    public AccountResource(AccountService accountService) {
        this.accountService = accountService;
    }

//    @GET
//    public AccountDto getAccount(@PathParam(Parameters.id) Long id) {
//        return accountService.get(id);
//    }

    @GetMapping
    public List<AccountDto> getAccounts() {
        return accountService.getAccounts();
    }

    @PostMapping(Paths.OPEN_DEBIT_ACCOUNT)
    public AccountDto openDebitAccount(@RequestBody AccountDto accountDto) throws JsonProcessingException {
        return accountService.openAccount(accountDto, AccountType.DEBIT);
    }

    @PostMapping(Paths.OPEN_CREDIT_ACCOUNT)
    public AccountDto openCreditAccount(@RequestBody AccountDto accountDto) throws JsonProcessingException {
        return accountService.openAccount(accountDto, AccountType.CREDIT);
    }

    @PostMapping(Paths.CLOSE_DEBIT_ACCOUNT)
    public void closeDebitAccount(@PathParam(Parameters.id) Long id) throws JsonProcessingException {
        accountService.closeAccount(id, AccountType.DEBIT);
    }

    @PostMapping(Paths.CLOSE_CREDIT_ACCOUNT)
    public void closeCreditAccount(@PathParam(Parameters.id) Long id) throws JsonProcessingException {
        accountService.closeAccount(id, AccountType.CREDIT);
    }
}
