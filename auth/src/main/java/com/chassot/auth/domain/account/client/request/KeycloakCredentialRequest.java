package com.chassot.auth.domain.account.client.request;

import com.chassot.auth.domain.account.enums.CredentialTypeEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class KeycloakCredentialRequest {

    @JsonProperty("temporary")
    private Boolean isTemporary;

    private CredentialTypeEnum type;

    @JsonProperty("value")
    private String password;

}
