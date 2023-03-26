package com.galinazabelina.core.core;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.galinazabelina.core.model.AccountType;

import java.math.BigDecimal;

public interface OperationsService {

    void changeBalance(Long accountId, BigDecimal changeValue, AccountType accountType) throws JsonProcessingException;

}
