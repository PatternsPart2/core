package com.galinazabelina.core.core;

import com.galinazabelina.core.api.dto.OperationDto;
import com.galinazabelina.core.model.entity.Operation;

import java.util.List;

public interface OperationsHistoryService {

    List<OperationDto> get (Long accountId);

}
