package com.campustwin.auth.config;

import com.campustwin.auth.entity.Role;
import com.campustwin.auth.entity.User;
import com.campustwin.auth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class DataSeeder implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {

        // Agar koi bhi ADMIN already hai toh seed mat karo
        boolean adminExists = userRepository.findAll()
                .stream()
                .anyMatch(u -> u.getRole() == Role.ADMIN);

        if (adminExists) {
            log.info("Admin already exists — skipping seed");
            return;
        }

        User admin = new User();
        admin.setName("Campus Admin");
        admin.setEmail("admin@lpu.in");
        admin.setUniversityId("ADMIN001");
        admin.setPassword(passwordEncoder.encode("Admin@1234"));
        admin.setRole(Role.ADMIN);
        admin.setDepartment("Administration");
        admin.setBlock("Admin Block");
        admin.setActive(true);

        userRepository.save(admin);
        log.info("Default admin seeded — email: admin@lpu.in | password: Admin@1234");
    }
}