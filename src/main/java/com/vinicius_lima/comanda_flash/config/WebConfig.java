package com.vinicius_lima.comanda_flash.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;


@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    public void addCorsMappings(CorsRegistry registry) {
        registry
                .addMapping("/**")
                .allowedMethods("HEAD", "GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS");
    }

}
