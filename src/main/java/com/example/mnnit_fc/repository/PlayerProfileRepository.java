package com.example.mnnit_fc.repository;

import com.example.mnnit_fc.entity.PlayerProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlayerProfileRepository extends JpaRepository<PlayerProfile, Long> {
    Optional<PlayerProfile> findByRegistrationNumber(String registrationNumber);
}
