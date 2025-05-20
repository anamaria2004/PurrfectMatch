package com.wad.firstmvc.config;

import java.math.BigDecimal;
import java.util.List;

import com.wad.firstmvc.domain.User;
import com.wad.firstmvc.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import com.wad.firstmvc.domain.Role;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {

        userRepository.deleteAll();
        // Initialize users if none exist
        if (userRepository.count() == 0) {
            User admin = new User("admin", passwordEncoder.encode("admin123"), Role.ADMIN);
            User adopter = new User("adopter", passwordEncoder.encode("adopter123"), Role.ADOPTER);
            userRepository.saveAll(List.of(admin, adopter));
        }

        // Initialize products if none exist

    }
}