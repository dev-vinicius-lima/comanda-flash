package com.vinicius_lima.comanda_flash;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@SpringBootApplication
@EnableSpringDataWebSupport(pageSerializationMode = EnableSpringDataWebSupport.PageSerializationMode.VIA_DTO)
public class ComandaFlashApplication {

    public static void main(String[] args) {
        SpringApplication.run(ComandaFlashApplication.class, args);
        System.out.println("PROGRAM COMANDA FLASH RUNNING...");
    }

}
