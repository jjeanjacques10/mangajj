package com.mangajj.mangacontrol;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableCaching
@EnableScheduling
@EnableFeignClients
@OpenAPIDefinition
@SpringBootApplication
public class MangaControlApplication {

    public static void main(String[] args) {
        SpringApplication.run(MangaControlApplication.class, args);
    }

}
