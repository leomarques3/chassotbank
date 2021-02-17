package com.chassot.auth.common.util;

import lombok.experimental.UtilityClass;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@UtilityClass
public class UriGeneratorUtil {

    public static URI get(String path, Object value) {
        return ServletUriComponentsBuilder.fromCurrentRequest()
                .path(path)
                .buildAndExpand(value)
                .toUri();
    }

}
