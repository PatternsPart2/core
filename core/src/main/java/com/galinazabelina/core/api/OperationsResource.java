package com.galinazabelina.core.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.galinazabelina.core.core.OperationsService;
import com.galinazabelina.core.core.Parameters;
import com.galinazabelina.core.model.AccountType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.*;
import java.math.BigDecimal;

@RestController
@RequestMapping(Paths.OPERATIONS)
public class OperationsResource {

    private final OperationsService operationsService;
    @Autowired
    public OperationsResource(OperationsService operationsService) {
        this.operationsService = operationsService;
    }

    @PatchMapping(Paths.DEBIT)
    public void changeBalanceDebitAccount(
            @PathParam(Parameters.id) Long accountId,
            @QueryParam(Parameters.changeValue) BigDecimal changeValue) throws JsonProcessingException {
        operationsService.changeBalance(accountId, changeValue, AccountType.DEBIT);
    }

    @PatchMapping(Paths.CREDIT)
    public void changeBalanceCreditAccount(
            @PathParam(Parameters.id) Long accountId,
            @QueryParam(Parameters.changeValue) BigDecimal changeValue) throws JsonProcessingException {
        operationsService.changeBalance(accountId, changeValue, AccountType.CREDIT);
    }

}
