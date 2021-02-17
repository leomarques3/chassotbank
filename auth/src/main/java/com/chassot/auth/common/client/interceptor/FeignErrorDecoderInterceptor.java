package com.chassot.auth.common.client.interceptor;

import com.chassot.auth.common.enums.ErrorCodeEnum;
import com.chassot.auth.common.exception.AuthException;
import com.chassot.auth.common.exception.BusinessException;
import com.chassot.auth.common.util.JsonUtil;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class FeignErrorDecoderInterceptor implements ErrorDecoder {

    @Override
    public Exception decode(String methodKey, Response response) {
        log.error("Error during client communication - Status code: {}, Origin: {}, Reason: {}", response.status(), methodKey, response.reason());
        HttpStatus httpStatus = HttpStatus.valueOf(response.status());
        switch (httpStatus) {
            case UNAUTHORIZED:
                return handleUnauthorized(httpStatus, methodKey, JsonUtil.extractBodyFrom(response));
            case CONFLICT:
                return handleConflict(httpStatus, methodKey, JsonUtil.extractBodyFrom(response));
            default:
                return new RuntimeException(response.reason());
        }
    }

    private Exception handleUnauthorized(HttpStatus httpStatus, String methodKey, String body) {
        log.error("Unauthorized - Status code: {}, Origin: {}, Body: {}", httpStatus, methodKey, body);
        return new AuthException(httpStatus, ErrorCodeEnum.AUTH_ERROR, body);
    }

    private Exception handleConflict(HttpStatus httpStatus, String methodKey, String body) {
        log.error("Conflict - Status code: {}, Origin: {}, Body: {}", httpStatus, methodKey, body);
        return new BusinessException(httpStatus, ErrorCodeEnum.BUSINESS_ERROR, body);
    }

}
