package com.example.mnnit_fc.entities;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "practice_sessions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PracticeSession {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "session_seq")
    @SequenceGenerator(name = "session_seq", sequenceName = "session_sequence", allocationSize = 1)
    @Column(name = "session_id")
    private Long sessionId;

    @Column(name = "session_date", nullable = false)
    private LocalDateTime sessionDate;

    @Column(name = "location")
    private String location;

    @Column(name = "focus_area")
    private String focusArea; // e.g., "Tactical", "Fitness", "Scrimmage"

    @Column(name = "notes")
    private String notes;
}