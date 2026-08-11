package com.example.mnnit_fc.entities;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "tournaments")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tournament {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tournament_seq")
    @SequenceGenerator(name = "tournament_seq", sequenceName = "tourn_sequence", allocationSize = 1)
    @Column(name = "tournament_id")
    private Long tournamentId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String status; // 'ACTIVE', 'COMPLETED', etc.

    @Column(name = "end_date")
    private LocalDateTime endDate;

    @OneToMany(mappedBy = "tournament", cascade = CascadeType.ALL)
    private List<Team> teams;

    @OneToMany(mappedBy = "tournament", cascade = CascadeType.ALL)
    private List<TournamentGroup> groups;
}