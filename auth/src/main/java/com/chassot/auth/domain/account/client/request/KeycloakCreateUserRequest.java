package com.chassot.auth.domain.account.client.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class KeycloakCreateUserRequest {

    private String username;
    private List<KeycloakCredentialRequest> credentials;
    private List<String> groups;
    private String firstName;
    private String lastName;
    private String email;

    @JsonProperty("emailVerified")
    private Boolean isEmailVerified;

    @JsonProperty("enabled")
    private Boolean isUserEnabled;

}
