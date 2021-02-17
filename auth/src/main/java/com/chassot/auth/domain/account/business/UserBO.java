package com.chassot.auth.domain.account.business;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserBO {

    private String username;
    private String password;
    private PersonBO person;

}
