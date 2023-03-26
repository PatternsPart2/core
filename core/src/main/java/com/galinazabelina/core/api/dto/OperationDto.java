package com.galinazabelina.core.api.dto;

import com.galinazabelina.core.model.OperationType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OperationDto {

    private Long id;

    private OperationType operationType;

    private Long accountId;

    private BigDecimal amountOfMoney;

    private ZonedDateTime timestamp;

}
