package com.chassot.auth.domain.authentication.service.impl;

import com.chassot.auth.domain.authentication.business.AuthenticationBO;
import com.chassot.auth.domain.authentication.client.KeycloakClient;
import com.chassot.auth.domain.authentication.client.response.KeycloakResponse;
import com.chassot.auth.domain.authentication.converter.AuthenticationConverter;
import com.chassot.auth.domain.authentication.entity.Token;
import com.chassot.auth.domain.authentication.enums.ProviderEnum;
import com.chassot.auth.domain.authentication.repository.TokenRepository;
import com.chassot.auth.domain.authentication.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Service
public class AdminAuthenticationServiceImpl implements AuthenticationService {

    private final KeycloakClient keycloakClient;
    private final TokenRepository tokenRepository;

    @Override
    public Token authenticate(AuthenticationBO authentication) {
        log.info("Authenticating keycloak administrator");
        Map<String, String> keycloakRequest = AuthenticationConverter.convertFrom(authentication.getUser().getUsername(),
                authentication.getUser().getPassword(), authentication.getGrantType(), authentication.getClient().getId());
        KeycloakResponse keycloakResponse = keycloakClient.authenticate(keycloakRequest);
        Token token = AuthenticationConverter.convertFrom(keycloakResponse, ProviderEnum.KEYCLOAK_ADMIN);
        return tokenRepository.save(token);
    }

}
