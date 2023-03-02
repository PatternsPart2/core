package com.galinazabelina.core.api.dto;

import com.galinazabelina.core.model.AccountType;
import com.galinazabelina.core.model.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountDto {

    private Long id;

    private User user;

    private String currencyCode;

    private BigDecimal balance;

    private Boolean isClosed;

    private AccountType accountType;

}
