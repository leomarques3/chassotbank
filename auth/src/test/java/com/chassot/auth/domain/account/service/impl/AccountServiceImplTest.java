package com.chassot.auth.domain.account.service.impl;

import com.chassot.auth.domain.account.business.PersonBO;
import com.chassot.auth.domain.account.business.UserBO;
import com.chassot.auth.domain.account.client.KeycloakAccountClient;
import com.chassot.auth.domain.account.client.request.KeycloakCreateUserRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class AccountServiceImplTest {

    @InjectMocks
    AccountServiceImpl accountServiceImpl;

    @Mock
    KeycloakAccountClient keycloakAccountClient;

    PersonBO person;
    UserBO user;

    @BeforeEach
    void initEach() {
        ReflectionTestUtils.setField(accountServiceImpl, "realm", "chassotbank");
        person = PersonBO.builder().documentNumber("10099910099").firstName("Test").lastName("Api").email("test.api@test1.com").build();
        user = UserBO.builder().person(person).username("10099910099").password("!Test123").build();
    }

    @DisplayName("Success - Successfully created user")
    @Test
    void shouldCreateNewUser_givenUserInformation() {
        accountServiceImpl.register(user);
        verify(keycloakAccountClient).createUser(anyString(), any(KeycloakCreateUserRequest.class));
    }
}
