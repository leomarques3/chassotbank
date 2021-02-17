package com.chassot.auth.domain.authentication.service.impl;

import com.chassot.auth.domain.authentication.business.AuthenticationBO;
import com.chassot.auth.domain.authentication.business.ClientBO;
import com.chassot.auth.domain.authentication.business.UserBO;
import com.chassot.auth.domain.authentication.client.KeycloakUserClient;
import com.chassot.auth.domain.authentication.client.response.KeycloakResponse;
import com.chassot.auth.domain.authentication.entity.Token;
import com.chassot.auth.domain.authentication.enums.ProviderEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserAuthenticationServiceImplTest {

    @InjectMocks
    UserAuthenticationServiceImpl userAuthenticationServiceImpl;

    @Mock
    KeycloakUserClient keycloakUserClient;

    ClientBO client;
    UserBO user;
    AuthenticationBO authentication;
    KeycloakResponse keycloakResponse;
    Token token;

    @BeforeEach
    void initEach() {
        ReflectionTestUtils.setField(userAuthenticationServiceImpl, "realm", "chassotbank");
        client = ClientBO.builder().id("user-login").secret("2b8c6350-ba7b-48e0-a086-d30bf19dbad7").build();
        user = UserBO.builder().username("10099910099").password("!Test123").build();
        authentication = AuthenticationBO.builder().grantType("password").client(client).user(user).build();
        keycloakResponse = KeycloakResponse.builder().accessToken("b57c9e96-d9c8-483e-8b68-1c23a3c79faf").expiresIn(7200L)
                .tokenType("bearer").build();
        token = Token.builder().type("bearer").value("b57c9e96-d9c8-483e-8b68-1c23a3c79faf").expiresIn(LocalDateTime.now()
                .plusSeconds(7200L)).provider(ProviderEnum.KEYCLOAK_USER).build();
    }

    @DisplayName("Success - Successfully authenticate user")
    @Test
    void shouldReturnUserToken_givenUserCredentials() {
        when(keycloakUserClient.authenticate(anyString(), any())).thenReturn(keycloakResponse);
        Token result = userAuthenticationServiceImpl.authenticate(authentication);
        assertEquals("b57c9e96-d9c8-483e-8b68-1c23a3c79faf", result.getValue());
    }

}
