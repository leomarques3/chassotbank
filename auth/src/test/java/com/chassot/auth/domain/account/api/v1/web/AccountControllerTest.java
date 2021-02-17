package com.chassot.auth.domain.account.api.v1.web;

import com.chassot.auth.common.enums.ErrorCodeEnum;
import com.chassot.auth.common.exception.AuthException;
import com.chassot.auth.common.exception.handler.GlobalExceptionHandler;
import com.chassot.auth.common.util.JsonUtil;
import com.chassot.auth.domain.account.api.v1.web.request.PersonRequest;
import com.chassot.auth.domain.account.api.v1.web.request.RegisterRequest;
import com.chassot.auth.domain.account.business.UserBO;
import com.chassot.auth.domain.account.service.AccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class AccountControllerTest {

    @InjectMocks
    AccountController accountController;

    @Mock
    AccountService accountService;

    MockMvc mockMvc;
    PersonRequest personRequest;
    RegisterRequest registerRequest;

    @BeforeEach
    void initEach() {
        mockMvc = MockMvcBuilders.standaloneSetup(accountController).setControllerAdvice(new GlobalExceptionHandler()).build();
        personRequest = PersonRequest.builder().documentNumber("10099910099").firstName("Test").lastName("Api").email("test.api@test1.com").build();
        registerRequest = RegisterRequest.builder().person(personRequest).password("!Test123").build();
    }

    @DisplayName("Success - Successfully created user")
    @Test
    void shouldCreateNewUser_givenUserInformation() throws Exception {
        doNothing().when(accountService).register(any(UserBO.class));
        mockMvc.perform(post("/v1/accounts")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(JsonUtil.asJsonString(registerRequest)))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", "http://localhost/v1/accounts/10099910099"));
    }

    @DisplayName("Failure - Failed to create user due invalid password format")
    @Test
    void shouldThrowMethodArgumentNotValidException_givenInvalidPassword() throws Exception {
        registerRequest.setPassword("!test123");
        mockMvc.perform(post("/v1/accounts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.asJsonString(registerRequest)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code", is(ErrorCodeEnum.INVALID_REQUEST_BODY.getValue())));
    }

    @DisplayName("Failure - Failed to create user due invalid administrator credentials")
    @Test
    void shouldThrowAuthException_givenInvalidAdministratorCredentials() throws Exception {
        doThrow(new AuthException(HttpStatus.UNAUTHORIZED, ErrorCodeEnum.AUTH_ERROR, "401")).when(accountService).register(any(UserBO.class));
        mockMvc.perform(post("/v1/accounts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.asJsonString(registerRequest)))
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.code", is(ErrorCodeEnum.AUTH_ERROR.getValue())));
    }

    @DisplayName("Failure - Failed to create user due existing username")
    @Test
    void shouldThrowBusinessException_givenExistingUsername() throws Exception {
        doThrow(new AuthException(HttpStatus.CONFLICT, ErrorCodeEnum.BUSINESS_ERROR, "409")).when(accountService).register(any(UserBO.class));
        mockMvc.perform(post("/v1/accounts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.asJsonString(registerRequest)))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.code", is(ErrorCodeEnum.BUSINESS_ERROR.getValue())));
    }

}
