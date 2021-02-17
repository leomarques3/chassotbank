package com.chassot.auth.common.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ErrorCodeEnum {

    INVALID_REQUEST_BODY("INVALID_REQUEST_BODY"),
    DATA_NOT_FOUND("DATA_NOT_FOUND"),
    CLIENT_REQUEST_ERROR("CLIENT_REQUEST_ERROR"),
    DATABASE_ERROR("DATABASE_ERROR"),
    BUSINESS_ERROR("BUSINESS_ERROR"),
    AUTH_ERROR("AUTH_ERROR"),
    IO_ERROR("IO_ERROR");

    private final String value;

    @JsonValue
    public String getValue() {
        return value;
    }

}
