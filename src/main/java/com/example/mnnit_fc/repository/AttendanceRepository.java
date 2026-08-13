package com.example.mnnit_fc.repositories;

import com.example.mnnit_fc.entities.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
}
