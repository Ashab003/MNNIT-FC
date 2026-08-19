package com.example.mnnit_fc.match.repo;

import com.example.mnnit_fc.match.entity.MatchStat;
import com.example.mnnit_fc.player.dto.PlayerStatsProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MatchStatRepository extends JpaRepository<MatchStat, Long> {
    @Query(value = """
        SELECT 
            COALESCE(SUM(ms.goals), 0) AS goals, 
            COALESCE(SUM(ms.assists), 0) AS assists, 
            COUNT(ms.match_id) AS matchesPlayed,
            COALESCE(SUM(ms.yellow_cards), 0) AS yellowCards,
            COALESCE(SUM(ms.red_cards), 0) AS redCards
                
        FROM match_stats ms
        JOIN player_profiles p ON ms.player_id = p.profile_id
        WHERE p.registration_number = :regNumber
        """,
            nativeQuery = true)
    PlayerStatsProjection getPlayerStats(@Param("regNumber") String regNumber);


}
