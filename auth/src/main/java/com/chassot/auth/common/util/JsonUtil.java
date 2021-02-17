package com.chassot.auth.common.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import feign.Response;
import lombok.experimental.UtilityClass;
import org.apache.commons.io.IOUtils;

import java.nio.charset.Charset;

@UtilityClass
public class JsonUtil {

    public static String asJsonString(final Object object) {
        try {
            return getObjectMapper().writeValueAsString(object);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static ObjectMapper getObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        objectMapper.findAndRegisterModules();
        return objectMapper;
    }

    public static String extractBodyFrom(Response response) {
        try {
            return IOUtils.toString(response.body().asReader(Charset.defaultCharset()));
        } catch (Exception ignore) {
            return "Empty Body";
        }
    }

}
