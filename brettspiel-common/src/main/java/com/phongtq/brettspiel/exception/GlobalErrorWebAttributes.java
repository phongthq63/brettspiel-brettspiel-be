package com.phongtq.brettspiel.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.phongtq.brettspiel.dto.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.reactive.error.DefaultErrorAttributes;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;

import java.util.Locale;
import java.util.Map;

/**
 * Created by Quach Thanh Phong
 * On 7/5/2023 - 1:34 PM
 */
@Slf4j
@Component
public class GlobalErrorWebAttributes extends DefaultErrorAttributes {

    private final ObjectMapper objectMapper = new ObjectMapper();


    @Bean
    public WebProperties.Resources resources() {
        return new WebProperties.Resources();
    }

    @Override
    public Map<String, Object> getErrorAttributes(ServerRequest request, ErrorAttributeOptions options) {
        String lang = request.headers().firstHeader(HttpHeaders.ACCEPT_LANGUAGE);
        Locale locale = lang == null || lang.isEmpty() ? Locale.US : Locale.forLanguageTag(lang);
        Throwable ex = getError(request);
        String message = ex.getMessage() == null || ex.getMessage().isBlank() ? "" : ex.getMessage();

        log.error("", ex);
        return objectMapper.convertValue(R.failed(message), Map.class);
    }

}
