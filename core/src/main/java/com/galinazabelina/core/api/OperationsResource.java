package com.galinazabelina.core.api;

import com.galinazabelina.core.core.OperationsService;
import com.galinazabelina.core.core.Parameters;
import com.galinazabelina.core.model.AccountType;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.*;

@Path(Paths.OPERATIONS)
public class OperationsResource {

    private final OperationsService operationsService;
    @Autowired
    public OperationsResource(OperationsService operationsService) {
        this.operationsService = operationsService;
    }

    @PATCH
    @Path(Paths.DEBIT)
    public void changeBalanceDebitAccount(
            @FormParam(Parameters.id) Long accountId,
            @FormParam(Parameters.changeValue) Long changeValue) {
        operationsService.changeBalance(accountId, changeValue, AccountType.DEBIT);
    }

    @PATCH
    @Path(Paths.CREDIT)
    public void changeBalanceCreditAccount(
            @FormParam(Parameters.id) Long accountId,
            @FormParam(Parameters.changeValue) Long changeValue) {
        operationsService.changeBalance(accountId, changeValue, AccountType.CREDIT);
    }

}
