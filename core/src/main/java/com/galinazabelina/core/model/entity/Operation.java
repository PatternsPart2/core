package com.galinazabelina.core.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.galinazabelina.core.model.OperationType;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "Operation")
@Table(name = "operations")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Operation {

    @JsonProperty("id")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JsonProperty("operation_type")
    @Column(name = "operation_type")
    @Enumerated(EnumType.STRING)
    private OperationType operationType;

    @JsonProperty("account_id")
    @Column(name = "account_id")
    private Long accountId;

    @JsonProperty("amount_of_money")
    @Column(name = "amount_of_money")
    private BigDecimal amountOfMoney;

    @JsonProperty("currency_code")
    @Column(name = "currency_code")
    private String currencyCode;

    @JsonProperty("operation_timestamp")
    @Column(name = "operation_timestamp")
    private ZonedDateTime timestamp;

}
