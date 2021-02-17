package com.chassot.auth.domain.account.api.v1.web;

import com.chassot.auth.common.util.JsonUtil;
import com.chassot.auth.common.util.UriGeneratorUtil;
import com.chassot.auth.domain.account.api.v1.web.request.RegisterRequest;
import com.chassot.auth.domain.account.business.UserBO;
import com.chassot.auth.domain.account.converter.AccountConverter;
import com.chassot.auth.domain.account.service.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/v1/accounts")
@RestController
public class AccountController {

    private final AccountService accountService;

    @PostMapping
    public ResponseEntity<Void> register(@Valid @RequestBody RegisterRequest registerRequest) {
        log.info("Creating a new user");
        log.debug("Register request: {}", JsonUtil.asJsonString(registerRequest));
        UserBO userBO = AccountConverter.convertFrom(registerRequest);
        accountService.register(userBO);
        return ResponseEntity.created(UriGeneratorUtil.get("/{username}", userBO.getUsername())).build();
    }

}
