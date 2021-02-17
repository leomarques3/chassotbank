package com.chassot.auth.domain.authentication.api.v1.web;

import com.chassot.auth.domain.authentication.api.v1.web.request.LoginRequest;
import com.chassot.auth.domain.authentication.business.AuthenticationBO;
import com.chassot.auth.domain.authentication.converter.AuthenticationConverter;
import com.chassot.auth.domain.authentication.entity.Token;
import com.chassot.auth.domain.authentication.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/v1/authentication")
@RestController
public class AuthenticationController {

    @Value("${keycloak.user.grant-type}")
    private String grantType;

    @Value("${keycloak.user.client-id}")
    private String clientId;

    @Value("${keycloak.user.client-secret}")
    private String clientSecret;

    private final AuthenticationService userAuthenticationServiceImpl;

    @PostMapping("/login")
    public ResponseEntity<Void> login(@Valid @RequestBody LoginRequest loginRequest) {
        log.info("Connecting user");
        log.debug("User: {}", loginRequest.getUsername());
        AuthenticationBO authentication = AuthenticationConverter.convertFrom(grantType, loginRequest, clientId, clientSecret);
        Token token = userAuthenticationServiceImpl.authenticate(authentication);
        return ResponseEntity.ok().header("Authorization", token.getValue()).build();
    }

}
