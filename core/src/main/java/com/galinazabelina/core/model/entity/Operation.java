package com.galinazabelina.core.model.entity;

import com.galinazabelina.core.model.OperationType;
import jakarta.persistence.*;
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
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "operation_type")
    @Enumerated(EnumType.STRING)
    private OperationType operationType;

    @Column(name = "account_id")
    private Long accountId;

    @Column(name = "amount_of_money")
    private Long amountOfMoney;

    @Column(name = "operation_timestamp")
    private ZonedDateTime timestamp;

}
