package com.vinicius_lima.comanda_flash.config;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;


@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI swaggerApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("Comanda Flash API")
                        .version("v1.0")
                        .description("API para gerenciamento de pedidos")
                        .contact(new Contact()
                                .name("Vinicius Lima")
                                .email("viniciuslimaes@hotmail.com")))
                .servers(List.of(
                        new Server().url("https://comanda-flash-production.up.railway.app")
                ));

    }

}