package com.chassot.auth.domain.authentication.client;

import com.chassot.auth.domain.authentication.client.response.KeycloakResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Map;

@FeignClient(value = "keycloak-authentication", url = "${keycloak.base-path}")
public interface KeycloakClient {

    @PostMapping(value = "${keycloak.authentication.path}", consumes = "application/x-www-form-urlencoded")
    KeycloakResponse authenticate(Map<String, ?> keycloakRequest);

}
