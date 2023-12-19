package ru.itis.employmentstub.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Configuration
public class WebConfig {
    @Bean
    public RestTemplate restTemplate(List<HttpMessageConverter<?>> converters) {
        return new RestTemplate(converters);
    }
}
