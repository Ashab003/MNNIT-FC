package com.example.mnnit_fc.practice;

import com.example.mnnit_fc.practice.repo.AttendanceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PracticeService {
    private final AttendanceRepository attendanceRepository;

    public double practicePercentage(Long playerId) {
           return attendanceRepository.totalAttendance(playerId) / 100.0;
    }
}
