package com.chassot.auth.domain.account.client;

import com.chassot.auth.domain.account.client.request.KeycloakCreateUserRequest;
import com.chassot.auth.domain.authentication.client.interceptor.AuthenticationInterceptor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "keycloak-account", url = "${keycloak.base-path}", configuration = AuthenticationInterceptor.class)
public interface KeycloakAccountClient {

    @PostMapping("${keycloak.account.path}")
    void createUser(@PathVariable String realm, @RequestBody KeycloakCreateUserRequest keycloakCreateUserRequest);

}
