package com.chassot.auth.common.constants;

import lombok.experimental.UtilityClass;

@UtilityClass
public class PatternConstant {

    public static final String CPF = "^[0-9]{11}$";
    public static final String EMAIL = "^([a-zA-Z0-9_\\-.]+)@([a-zA-Z0-9_\\-.]+)\\.([a-zA-Z]{2,5})$";
    public static final String PASSWORD = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&+=*])(?=\\S+$).{8,}$";

}
