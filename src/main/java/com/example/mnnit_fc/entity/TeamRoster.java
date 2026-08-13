package com.example.mnnit_fc.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "team_rosters")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TeamRoster {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "roster_seq")
    @SequenceGenerator(name = "roster_seq", sequenceName = "roster_sequence", allocationSize = 1)
    @Column(name = "roster_id")
    private Long rosterId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id", nullable = false)
    private Team team;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "player_id", nullable = false)
    private PlayerProfile player;
}