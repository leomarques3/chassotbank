package com.chassot.auth.domain.authentication.service;

import com.chassot.auth.domain.authentication.business.AuthenticationBO;
import com.chassot.auth.domain.authentication.entity.Token;

public interface AuthenticationService {

    Token authenticate(AuthenticationBO authenticationBO);

}
