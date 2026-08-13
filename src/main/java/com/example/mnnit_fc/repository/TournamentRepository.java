package com.example.mnnit_fc.repositories;

import com.example.mnnit_fc.entities.TournamentGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TournamentRepository extends JpaRepository<TournamentGroup, Long> {
}
