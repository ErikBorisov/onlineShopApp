package com.example.onlineapp;

import com.example.onlineapp.models.User;
import com.example.onlineapp.models.enums.Role;
import com.example.onlineapp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class OnlineAppApplication implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    public static void main(String[] args) {
        SpringApplication.run(OnlineAppApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        if (userRepository.findByEmail("admin@gmail.com").isEmpty()) {
            userRepository.save(User.builder()
                    .firstName("Admin")
                    .lastName("Adminov")
                    .email("admin@gmail.com")
                    .role(Role.ADMIN)
                    .password(passwordEncoder.encode("000"))
                    .build());
        }
    }

}
