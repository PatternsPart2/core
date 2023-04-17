package com.galinazabelina.core.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.galinazabelina.core.core.OperationsService;
import com.galinazabelina.core.core.Parameters;
import com.galinazabelina.core.model.AccountType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PatchMapping(Paths.DEBIT + "/{id}")
    public void changeBalanceDebitAccount(
            @PathVariable Long id,
            @RequestParam(Parameters.changeValue) BigDecimal changeValue) throws JsonProcessingException {
        operationsService.changeBalance(id, changeValue, AccountType.DEBIT);
    }

    @PatchMapping(Paths.CREDIT + "/{id}")
    public void changeBalanceCreditAccount(
            @PathVariable Long id,
            @RequestParam(Parameters.changeValue) BigDecimal changeValue) throws JsonProcessingException {
        operationsService.changeBalance(id, changeValue, AccountType.CREDIT);
    }

}
