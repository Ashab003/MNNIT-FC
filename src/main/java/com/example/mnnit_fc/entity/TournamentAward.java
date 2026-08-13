package com.example.mnnit_fc.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tournament_awards")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TournamentAward {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "award_seq")
    @SequenceGenerator(name = "award_seq", sequenceName = "award_sequence", allocationSize = 1)
    @Column(name = "award_id")
    private Long awardId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tournament_id", nullable = false)
    private Tournament tournament;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "player_id", nullable = false)
    private PlayerProfile player;

    @Column(name = "award_name", nullable = false)
    private String awardName; // e.g., "Player of the Tournament", "Best Goalkeeper"
}
