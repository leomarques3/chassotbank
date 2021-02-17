package com.chassot.auth.config;

import com.chassot.auth.common.util.JsonUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JsonConfig {

    @Bean
    public ObjectMapper objectMapper() {
        return JsonUtil.getObjectMapper();
    }

}
