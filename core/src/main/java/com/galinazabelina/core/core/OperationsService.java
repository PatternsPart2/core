package com.galinazabelina.core.core;

import com.galinazabelina.core.model.AccountType;
import com.galinazabelina.core.model.OperationType;
import com.galinazabelina.core.model.entity.Operation;

import java.util.List;

public interface OperationsService {

    void changeBalance(Long accountId, Long changeValue, AccountType accountType);

}
