package com.morpheus.cursomc2.config;

import com.morpheus.cursomc2.services.DBService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.text.ParseException;

@Configuration
@Profile("test")
@RequiredArgsConstructor
public class TestConfig {
    private final DBService dbService;

    @Bean
    public boolean instantiateDatabase() throws ParseException {
        dbService.instatiateTestDatabase();
        return true;
    }

}
