package com.chassot.auth.domain.account.business;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PersonBO {

    private String documentNumber;
    private String firstName;
    private String lastName;
    private String email;

}
