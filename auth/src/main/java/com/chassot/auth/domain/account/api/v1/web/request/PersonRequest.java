package com.chassot.auth.domain.account.api.v1.web.request;

import com.chassot.auth.common.constants.PatternConstant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PersonRequest {

    @Pattern(regexp = PatternConstant.CPF)
    @NotBlank
    private String documentNumber;

    @Size(min = 3, max = 60)
    @NotBlank
    private String firstName;

    @Size(min = 3, max = 60)
    @NotBlank
    private String lastName;

    @Pattern(regexp = PatternConstant.EMAIL)
    @NotBlank
    private String email;

}
