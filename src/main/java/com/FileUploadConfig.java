package com;

import com.service.StorageService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public interface FileUploadConfig {

    @Bean
    default CommandLineRunner init(StorageService storageService){
        return (args) -> {
            storageService.init();
        };
    };

}
