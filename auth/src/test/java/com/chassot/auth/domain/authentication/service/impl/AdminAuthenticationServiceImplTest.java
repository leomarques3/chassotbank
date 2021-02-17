package com.chassot.auth.domain.authentication.service.impl;

import com.chassot.auth.domain.authentication.business.AuthenticationBO;
import com.chassot.auth.domain.authentication.business.ClientBO;
import com.chassot.auth.domain.authentication.business.UserBO;
import com.chassot.auth.domain.authentication.client.KeycloakClient;
import com.chassot.auth.domain.authentication.client.response.KeycloakResponse;
import com.chassot.auth.domain.authentication.entity.Token;
import com.chassot.auth.domain.authentication.enums.ProviderEnum;
import com.chassot.auth.domain.authentication.repository.TokenRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AdminAuthenticationServiceImplTest {

    @InjectMocks
    AdminAuthenticationServiceImpl adminAuthenticationServiceImpl;

    @Mock
    KeycloakClient keycloakClient;

    @Mock
    TokenRepository tokenRepository;

    ClientBO client;
    UserBO user;
    AuthenticationBO authentication;
    KeycloakResponse keycloakResponse;
    Token token;

    @BeforeEach
    void initEach() {
        client = ClientBO.builder().id("admin-cli").secret(null).build();
        user = UserBO.builder().username("admin").password("admin").build();
        authentication = AuthenticationBO.builder().grantType("password").client(client).user(user).build();
        keycloakResponse = KeycloakResponse.builder().accessToken("b57c9e96-d9c8-483e-8b68-1c23a3c79faf").expiresIn(7200L)
                .tokenType("bearer").build();
        token = Token.builder().type("bearer").value("b57c9e96-d9c8-483e-8b68-1c23a3c79faf").expiresIn(LocalDateTime.now()
                .plusSeconds(7200L)).provider(ProviderEnum.KEYCLOAK_ADMIN).build();
    }

    @DisplayName("Success - Successfully authenticate administrator")
    @Test
    void shouldReturnAdminToken_givenAdminCredentials() {
        when(keycloakClient.authenticate(any())).thenReturn(keycloakResponse);
        when(tokenRepository.save(any(Token.class))).thenReturn(token);
        Token result = adminAuthenticationServiceImpl.authenticate(authentication);
        assertEquals("b57c9e96-d9c8-483e-8b68-1c23a3c79faf", result.getValue());
    }

}
