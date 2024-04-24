package com.example.dominantsoftdevelopment.init;
import com.example.dominantsoftdevelopment.model.User;
import com.example.dominantsoftdevelopment.model.enums.Roles;
import com.example.dominantsoftdevelopment.model.enums.Status;
import com.example.dominantsoftdevelopment.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class LoadDatabase {

    private final UserRepository adminRepository;

    @Bean
    CommandLineRunner initDatabase() {
        return args -> {

            User admin = User.builder()
                    .firstName("Admin")
                    .lastName("Adminov")
                    .password(new BCryptPasswordEncoder().encode("123"))
                    .phoneNumber("+998904430302")
                    .roles(Roles.ADMIN)
                    .status(Status.ONLINE)
                    .email("no0404ir@gmail.com")
                    .build();
            if (adminRepository.findByPhoneNumber("+998904430302").isEmpty())
                adminRepository.save(admin);
        };
    }
}
