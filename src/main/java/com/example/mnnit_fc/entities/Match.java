package com.example.mnnit_fc.entities;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "matches")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "match_seq")
    @SequenceGenerator(name = "match_seq", sequenceName = "match_sequence", allocationSize = 1)
    @Column(name = "match_id")
    private Long matchId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tournament_id", nullable = false)
    private Tournament tournament;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "home_team_id", nullable = false)
    private Team homeTeam;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "away_team_id", nullable = false)
    private Team awayTeam;

    @Column(name = "match_date")
    private LocalDateTime matchDate;

    @Column(name = "match_stage", nullable = false)
    private String matchStage; // 'GROUP_STAGE' or 'KNOCKOUT'

    // Nullable: Only populated if matchStage == 'GROUP_STAGE'
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id")
    private TournamentGroup group;

    // Nullable: Only populated if matchStage == 'KNOCKOUT'
    @Column(name = "knockout_round")
    private String knockoutRound; // 'Quarter-Final', 'Semi-Final', etc.
}