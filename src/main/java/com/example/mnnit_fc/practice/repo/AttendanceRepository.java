package com.example.mnnit_fc.practice.repo;

import com.example.mnnit_fc.practice.entity.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
    @Query(
            value = """
                SELECT COUNT(*)
                FROM attendance a
                JOIN player_profiles p
                    ON a.player_id = p.profile_id
                WHERE p.profile_id = :playerId
                  AND a.is_present = true
                """,
            nativeQuery = true
    )
    Long totalAttendance(@Param("playerId") Long playerId);
}
