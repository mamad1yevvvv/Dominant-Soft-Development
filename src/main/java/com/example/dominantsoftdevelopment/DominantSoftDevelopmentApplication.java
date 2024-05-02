package com.example.dominantsoftdevelopment;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Optional;

@SpringBootApplication
@RequiredArgsConstructor
@EnableJpaAuditing
public class DominantSoftDevelopmentApplication /*implements CommandLineRunner*/ {
    private final MockDataGenerator mockDataGenerator;
    public static void main(String[] args) {
        SpringApplication.run(DominantSoftDevelopmentApplication.class, args);
    }


   /* @Override
    public void run(String... args) throws Exception {
        mockDataGenerator.generate();
    }*/

}
