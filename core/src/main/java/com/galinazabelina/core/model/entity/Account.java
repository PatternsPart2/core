package com.galinazabelina.core.model.entity;

import com.galinazabelina.core.model.AccountType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity(name = "Account")
@Table(name = "accounts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "currency_code", nullable = false)
    private String currencyCode;

    @Column(name = "balance", nullable = false)
    private BigDecimal balance;

    @Column(name = "is_closed", nullable = false)
    private Boolean isClosed;

    @Column(name = "account_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private AccountType accountType;

}
