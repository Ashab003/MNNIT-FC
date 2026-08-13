package com.example.mnnit_fc.repository;

import com.example.mnnit_fc.entity.TournamentGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TournamentRepository extends JpaRepository<TournamentGroup, Long> {
}
