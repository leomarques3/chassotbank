package com.chassot.auth.config;

import feign.codec.Encoder;
import feign.form.FormEncoder;
import feign.okhttp.OkHttpClient;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {

    @Autowired
    private ObjectFactory<HttpMessageConverters> messageConverters;

    @Bean
    public OkHttpClient feignClient() {
        return new OkHttpClient();
    }

    @Bean
    public Encoder formEncoder() {
        return new FormEncoder(new SpringEncoder(messageConverters));
    }

}
