package com.galinazabelina.core.model;

import lombok.Getter;

@Getter
public enum AccountType {
    CREDIT("credit"),
    DEBIT("debit");

    private String name;
    AccountType(String name) {
        this.name = name;
    }

}
