package com.example.mnnit_fc.tournament.repo;

import com.example.mnnit_fc.tournament.entity.TournamentGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TournamentRepository extends JpaRepository<TournamentGroup, Long> {
}
