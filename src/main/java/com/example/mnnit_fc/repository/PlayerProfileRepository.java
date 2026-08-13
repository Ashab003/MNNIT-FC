package com.example.mnnit_fc.repositories;

import com.example.mnnit_fc.entities.PlayerProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerProfileRepository extends JpaRepository<PlayerProfile, Long> {
}
