package com.galinazabelina.core.model;

import lombok.Getter;

@Getter
public enum OperationType {
    OPEN_ACCOUNT("openAccount"),

    REPLENISHMENT("replenishment"),

    WITHDRAW("withdraw"),

    CLOSE_ACCOUNT("closeAccount");

    private String name;
    OperationType(String name) {
        this.name = name;
    }

}
