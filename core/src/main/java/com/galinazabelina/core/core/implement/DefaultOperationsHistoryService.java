package com.galinazabelina.core.core.implement;

import com.galinazabelina.core.api.dto.OperationDto;
import com.galinazabelina.core.core.OperationsHistoryService;
import com.galinazabelina.core.core.repository.OperationsHistoryRepository;
import com.galinazabelina.core.model.entity.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DefaultOperationsHistoryService implements OperationsHistoryService {

    private final OperationsHistoryRepository operationsHistoryRepository;

    @Override
    public List<OperationDto> get(Long accountId) {
        List<Operation> operations = operationsHistoryRepository.findAllByAccountId(accountId);
        return operations.stream().map(this::entityToDto).toList();
    }

    private OperationDto entityToDto(Operation operation) {
        return new OperationDto(
                operation.getId(),
                operation.getOperationType(),
                operation.getAccountId(),
                operation.getAmountOfMoney(),
                operation.getTimestamp()
        );
    }

}
