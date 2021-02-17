package com.chassot.auth.domain.authentication.business;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthenticationBO {

    private String grantType;
    private UserBO user;
    private ClientBO client;

}
