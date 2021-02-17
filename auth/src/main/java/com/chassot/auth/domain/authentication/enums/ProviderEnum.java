package com.chassot.auth.domain.authentication.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ProviderEnum {

    KEYCLOAK_ADMIN("KEYCLOAK_ADMIN"),
    KEYCLOAK_USER("KEYCLOAK_USER");

    private final String value;

    @JsonValue
    public String getValue() {
        return value;
    }

}
