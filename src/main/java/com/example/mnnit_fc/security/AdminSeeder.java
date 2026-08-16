package com.example.mnnit_fc.security;

import com.example.mnnit_fc.entity.PlayerProfile;
import com.example.mnnit_fc.enums.*;
import com.example.mnnit_fc.entity.User;
import com.example.mnnit_fc.repository.PlayerProfileRepository;
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
    @Transactional // Ensures both user and profile save successfully together
    public CommandLineRunner initSuperAdmin(
            UserRepository userRepository,
            PlayerProfileRepository playerProfileRepository, // Added this dependency!
            PasswordEncoder passwordEncoder) {

        return args -> {

            // 1. Check if any admin already exists
            if (userRepository.countByRole(Role.ROLE_ADMIN) == 0) {

                log.info("No Admin found in database. Initializing default Super Admin...");

                // --- 2. Create the Super Admin User ---
                User superAdmin = new User();

                superAdmin.setUserId("20233536");
                superAdmin.setName("Mohammad Ashab");
                superAdmin.setEmail("mohdashab043@gmail.com");
                superAdmin.setPhoneNumber("7042176065");
                superAdmin.setAcademicBranch(AcademicBranch.COMPUTER_SCIENCE_AND_ENGINEERING);
                superAdmin.setGraduationYear(2027);
                superAdmin.setDateOfBirth(java.time.LocalDate.of(2005, 9, 20)); // YYYY, MM, DD format

                superAdmin.setPassword(passwordEncoder.encode("Admin@MnnitFc2026_Ashab"));
                superAdmin.setRole(Role.ROLE_ADMIN);
                superAdmin.setIsApproved(true);

                // Save User first
                userRepository.save(superAdmin);

                // --- 3. Create the Super Admin Player Profile ---
                PlayerProfile adminProfile = new PlayerProfile();

                adminProfile.setUser(superAdmin);
                adminProfile.setRegistrationNumber(superAdmin.getUserId()); // The bridge anchor!
                adminProfile.setPlayerName(superAdmin.getName());
                adminProfile.setPosition(Position.CM);
                adminProfile.setPreferredFoot(PreferredFoot.RIGHT);
                adminProfile.setJerseyNumber(7);
                adminProfile.setSquadStatus(SquadStatus.MNNIT_SQUAD); // Core team member

                // Save Profile
                playerProfileRepository.save(adminProfile);

                log.info("=================================================");
                log.info("SUPER ADMIN & PROFILE GENERATED: {}", superAdmin.getName());
                log.info("=================================================");

            } else {
                log.info("Admin account already exists. Skipping initialization.");
            }
        };
    }
}