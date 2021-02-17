package com.chassot.auth.domain.account.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum CredentialTypeEnum {

    PASSWORD("password");

    private final String value;

    @JsonValue
    public String getValue() {
        return value;
    }

}
