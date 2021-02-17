package com.chassot.auth.domain.authentication.client;

import com.chassot.auth.domain.authentication.client.interceptor.AuthenticationInterceptor;
import com.chassot.auth.domain.authentication.client.response.KeycloakResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Map;

@FeignClient(value = "keycloak-user", url = "${keycloak.base-path}", configuration = AuthenticationInterceptor.class)
public interface KeycloakUserClient {

    @PostMapping(value = "${keycloak.user.path}", consumes = "application/x-www-form-urlencoded")
    KeycloakResponse authenticate(@PathVariable String realm, Map<String, ?> keycloakRequest);

}
