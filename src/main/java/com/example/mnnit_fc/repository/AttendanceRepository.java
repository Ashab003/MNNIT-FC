package com.example.mnnit_fc.repository;

import com.example.mnnit_fc.entity.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
}
