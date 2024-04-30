package com.example.dominantsoftdevelopment;

import com.example.dominantsoftdevelopment.utils.CommonUtils;
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

    @Bean
    public AuditorAware<Long> auditorAware() {
        return () -> Optional.of(CommonUtils.getCurrentUserFromContext().getId());
    }


}
