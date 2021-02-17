package com.chassot.auth.common.exception.handler;

import com.chassot.auth.common.api.v1.web.response.ErrorResponse;
import com.chassot.auth.common.api.v1.web.response.FieldResponse;
import com.chassot.auth.common.enums.ErrorCodeEnum;
import com.chassot.auth.common.exception.AuthException;
import com.chassot.auth.common.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers,
                                                                  HttpStatus status, WebRequest request) {
        log.error("MethodArgumentNotValidException: {}", ex.getMessage());
        List<FieldResponse> fields = new ArrayList<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> fields.add(new FieldResponse(error.getField(), error.getDefaultMessage())));
        log.error("Fields with error: {}", fields);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(ErrorCodeEnum.INVALID_REQUEST_BODY,
                "Invalid request body content", fields));
    }

    @ExceptionHandler(AuthException.class)
    public ResponseEntity<Object> handleAuth(AuthException ex) {
        log.error("AuthException: {}", ex.getMessage());
        return ResponseEntity.status(ex.getHttpStatus()).body(new ErrorResponse(ex.getErrorCode(), ex.getMessage(), null));
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<Object> handleBusiness(BusinessException ex) {
        log.error("BusinessException: {}", ex.getMessage());
        return ResponseEntity.status(ex.getHttpStatus()).body(new ErrorResponse(ex.getErrorCode(), ex.getMessage(), null));
    }

}
