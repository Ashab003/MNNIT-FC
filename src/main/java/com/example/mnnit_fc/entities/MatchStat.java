package com.example.mnnit_fc.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "match_stats")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MatchStat {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "stat_seq")
    @SequenceGenerator(name = "stat_seq", sequenceName = "stat_sequence", allocationSize = 1)
    @Column(name = "stat_id")
    private Long statId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "match_id", nullable = false)
    private Match match;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "player_id", nullable = false)
    private PlayerProfile player;

    @Column(nullable = false)
    private Integer goals = 0;

    @Column(nullable = false)
    private Integer assists = 0;

    @Column(name = "yellow_cards", nullable = false)
    private Integer yellowCards = 0;

    @Column(name = "red_cards", nullable = false)
    private Integer redCards = 0;

    @Column(name = "minutes_played")
    private Integer minutesPlayed;
}