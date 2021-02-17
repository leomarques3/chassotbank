package com.chassot.auth.domain.account.api.v1.web.request;

import com.chassot.auth.common.constants.PatternConstant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisterRequest {

    @Valid
    @NotNull
    private PersonRequest person;

    @Pattern(regexp = PatternConstant.PASSWORD)
    @NotBlank
    private String password;

}
