package com.chassot.auth.domain.authentication.client.interceptor;

import com.chassot.auth.domain.authentication.business.AuthenticationBO;
import com.chassot.auth.domain.authentication.converter.AuthenticationConverter;
import com.chassot.auth.domain.authentication.entity.Token;
import com.chassot.auth.domain.authentication.service.AuthenticationService;
import feign.RequestInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Component
public class AuthenticationInterceptor {

    @Value("${keycloak.authentication.username}")
    private String username;

    @Value("${keycloak.authentication.password}")
    private String password;

    @Value("${keycloak.authentication.grant-type}")
    private String grantType;

    @Value("${keycloak.authentication.client-id}")
    private String clientId;

    private final AuthenticationService adminAuthenticationServiceImpl;

    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> {
            AuthenticationBO authenticationBO = AuthenticationConverter.convertFrom(grantType, username, password, clientId, null);
            Token token = adminAuthenticationServiceImpl.authenticate(authenticationBO);
            String formattedToken = String.format("Bearer %s", token.getValue());
            requestTemplate.header("Authorization", formattedToken);
        };
    }

    //TODO: usar refresh token

}
