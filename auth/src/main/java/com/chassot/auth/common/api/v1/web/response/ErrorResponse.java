package com.chassot.auth.common.api.v1.web.response;

import com.chassot.auth.common.enums.ErrorCodeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorResponse {

    private ErrorCodeEnum code;
    private String message;
    private List<FieldResponse> fields;

}
