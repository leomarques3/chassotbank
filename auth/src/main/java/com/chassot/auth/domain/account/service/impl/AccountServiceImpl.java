package com.chassot.auth.domain.account.service.impl;

import com.chassot.auth.domain.account.business.UserBO;
import com.chassot.auth.domain.account.client.KeycloakAccountClient;
import com.chassot.auth.domain.account.client.request.KeycloakCreateUserRequest;
import com.chassot.auth.domain.account.converter.AccountConverter;
import com.chassot.auth.domain.account.service.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class AccountServiceImpl implements AccountService {

    @Value("${keycloak.account.realm}")
    private String realm;

    private final KeycloakAccountClient keycloakAccountClient;

    @Override
    public void register(UserBO userBO) {
        log.info("Creating a new user");
        log.debug("User information: {}", userBO);
        KeycloakCreateUserRequest keycloakCreateUserRequest = AccountConverter.convertFrom(userBO);
        keycloakAccountClient.createUser(realm, keycloakCreateUserRequest);
    }

}
