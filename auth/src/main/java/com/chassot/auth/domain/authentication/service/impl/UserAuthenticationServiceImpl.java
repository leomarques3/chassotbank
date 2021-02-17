package com.chassot.auth.domain.authentication.service.impl;

import com.chassot.auth.domain.authentication.business.AuthenticationBO;
import com.chassot.auth.domain.authentication.client.KeycloakUserClient;
import com.chassot.auth.domain.authentication.client.response.KeycloakResponse;
import com.chassot.auth.domain.authentication.converter.AuthenticationConverter;
import com.chassot.auth.domain.authentication.entity.Token;
import com.chassot.auth.domain.authentication.enums.ProviderEnum;
import com.chassot.auth.domain.authentication.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserAuthenticationServiceImpl implements AuthenticationService {

    @Value("${keycloak.user.realm}")
    private String realm;

    private final KeycloakUserClient keycloakUserClient;

    @Override
    public Token authenticate(AuthenticationBO authenticationBO) {
        log.info("Authenticating user");
        Map<String, String> keycloakRequest = AuthenticationConverter.convertFrom(authenticationBO.getUser(),
                authenticationBO.getGrantType(), authenticationBO.getClient());
        KeycloakResponse keycloakResponse = keycloakUserClient.authenticate(realm, keycloakRequest);
        return AuthenticationConverter.convertFrom(keycloakResponse, ProviderEnum.KEYCLOAK_USER);
    }

}
