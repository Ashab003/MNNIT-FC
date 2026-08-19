package com.example.mnnit_fc.match.repo;

import com.example.mnnit_fc.match.entity.Match;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatchRepository extends JpaRepository<Match, Long> {
}
