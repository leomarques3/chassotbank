package com.chassot.auth.common.exception;

import com.chassot.auth.common.enums.ErrorCodeEnum;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class AuthException extends RuntimeException {

    private HttpStatus httpStatus;
    private ErrorCodeEnum errorCode;

    public AuthException(HttpStatus httpStatus, ErrorCodeEnum errorCode, String message) {
        super(message);
        this.httpStatus = httpStatus;
        this.errorCode = errorCode;
    }

}
