package com.example.mnnit_fc.player.repo;

import com.example.mnnit_fc.player.entity.PlayerProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlayerProfileRepository extends JpaRepository<PlayerProfile, Long> {
    Optional<PlayerProfile> findByRegistrationNumber(String registrationNumber);
}
