package com.chassot.auth.domain.account.converter;

import com.chassot.auth.domain.account.api.v1.web.request.PersonRequest;
import com.chassot.auth.domain.account.api.v1.web.request.RegisterRequest;
import com.chassot.auth.domain.account.business.PersonBO;
import com.chassot.auth.domain.account.business.UserBO;
import com.chassot.auth.domain.account.client.request.KeycloakCreateUserRequest;
import com.chassot.auth.domain.account.client.request.KeycloakCredentialRequest;
import com.chassot.auth.domain.account.enums.CredentialTypeEnum;

import java.util.List;

public final class AccountConverter {

    public static UserBO convertFrom(RegisterRequest registerRequest) {
        return UserBO.builder()
                .username(registerRequest.getPerson().getDocumentNumber())
                .password(registerRequest.getPassword())
                .person(convertFrom(registerRequest.getPerson()))
                .build();
    }

    public static KeycloakCreateUserRequest convertFrom(UserBO userBO) {
        return KeycloakCreateUserRequest.builder()
                .username(userBO.getUsername())
                .credentials(List.of(convertFrom(userBO.getPassword())))
                .groups(List.of("users"))
                .firstName(userBO.getPerson().getFirstName())
                .lastName(userBO.getPerson().getLastName())
                .email(userBO.getPerson().getEmail())
                .isEmailVerified(false)
                .isUserEnabled(true)
                .build();
    }

    private static PersonBO convertFrom(PersonRequest personRequest) {
        return PersonBO.builder()
                .documentNumber(personRequest.getDocumentNumber())
                .firstName(personRequest.getFirstName())
                .lastName(personRequest.getLastName())
                .email(personRequest.getEmail())
                .build();
    }

    private static KeycloakCredentialRequest convertFrom(String password) {
        return KeycloakCredentialRequest.builder()
                .isTemporary(false)
                .password(password)
                .type(CredentialTypeEnum.PASSWORD)
                .build();
    }

}
