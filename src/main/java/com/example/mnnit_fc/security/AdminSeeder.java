package com.example.mnnit_fc.security;

import com.example.mnnit_fc.enums.Role;
import com.example.mnnit_fc.entity.User;
import com.example.mnnit_fc.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class AdminSeeder {

    @Bean
    @Transactional
    public CommandLineRunner initSuperAdmin(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {

            // 1. Check if any admin already exists
            if (userRepository.countByRole(Role.ROLE_ADMIN) == 0) {

                log.info("No Admin found in database. Initializing default Super Admin...");

                User superAdmin = new User();

                // 2. Set your personal admin details
                superAdmin.setUserId(20233536L); // Use your actual registration number here
                superAdmin.setName("Mohammad Ashab");
                superAdmin.setEmail("mohdashab043@gmail.com");
                superAdmin.setAcademicBranch("Computer Science and Engineering");

                // 3. Critically important fields
                superAdmin.setPassword(passwordEncoder.encode("Admin@MnnitFc2026_Ashab"));
                superAdmin.setRole(Role.ROLE_ADMIN);
                superAdmin.setIsApproved(true); // Must be true so you can actually log in!

                // 4. Save to Neon PostgreSQL
                userRepository.save(superAdmin);

                log.info("=================================================");
                log.info("SUPER ADMIN GENERATED: {}", superAdmin.getName());
                log.info("=================================================");
            } else {
                log.info("Admin account already exists. Skipping initialization.");
            }
        };
    }
}