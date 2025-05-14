package com.patient.module.infrastructure.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.patient.module.infrastructure.security.TokenContextHolder;

import feign.RequestInterceptor;

@Configuration
public class FeignConfig {


    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> {
            String token = TokenContextHolder.getToken();
            if (token != null) {
                requestTemplate.header("Authorization", "Bearer " + token);
            }
        };
    }
    
}
