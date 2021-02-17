package com.chassot.auth.domain.authentication.converter;

import com.chassot.auth.domain.authentication.api.v1.web.request.LoginRequest;
import com.chassot.auth.domain.authentication.business.AuthenticationBO;
import com.chassot.auth.domain.authentication.business.ClientBO;
import com.chassot.auth.domain.authentication.business.UserBO;
import com.chassot.auth.domain.authentication.client.response.KeycloakResponse;
import com.chassot.auth.domain.authentication.entity.Token;
import com.chassot.auth.domain.authentication.enums.ProviderEnum;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public final class AuthenticationConverter {

    public static Map<String, String> convertFrom(String username, String password, String grantType, String clientId) {
        Map<String, String> keycloakRequest = new HashMap<>();
        keycloakRequest.put("username", username);
        keycloakRequest.put("password", password);
        keycloakRequest.put("grant_type", grantType);
        keycloakRequest.put("client_id", clientId);
        return keycloakRequest;
    }

    public static Map<String, String> convertFrom(UserBO userBO, String grantType, ClientBO clientBO) {
        Map<String, String> keycloakRequest = new HashMap<>();
        keycloakRequest.put("username", userBO.getUsername());
        keycloakRequest.put("password", userBO.getPassword());
        keycloakRequest.put("grant_type", grantType);
        keycloakRequest.put("client_id", clientBO.getId());
        keycloakRequest.put("client_secret", clientBO.getSecret());
        return keycloakRequest;
    }

    public static Token convertFrom(KeycloakResponse keycloakResponse, ProviderEnum provider) {
        return Token.builder()
                .type(keycloakResponse.getTokenType())
                .value(keycloakResponse.getAccessToken())
                .expiresIn(LocalDateTime.now().plusSeconds(keycloakResponse.getExpiresIn()))
                .provider(provider)
                .build();
    }

    public static AuthenticationBO convertFrom(String grantType, LoginRequest loginRequest, String clientId, String clientSecret) {
        return AuthenticationBO.builder()
                .grantType(grantType)
                .user(UserBO.builder().username(loginRequest.getUsername()).password(loginRequest.getPassword()).build())
                .client(ClientBO.builder().id(clientId).secret(clientSecret).build())
                .build();
    }

    public static AuthenticationBO convertFrom(String grantType, String username, String password, String clientId, String clientSecret) {
        return AuthenticationBO.builder()
                .grantType(grantType)
                .user(UserBO.builder().username(username).password(password).build())
                .client(ClientBO.builder().id(clientId).secret(clientSecret).build())
                .build();
    }

}
