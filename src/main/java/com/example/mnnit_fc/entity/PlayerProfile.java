package com.example.mnnit_fc.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "player_profiles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PlayerProfile {

    @Id
    @Column(name = "player_id")
    private Long playerId;

    @OneToOne
    @MapsId // Tells JPA to use the User's ID as this entity's PK
    @JoinColumn(name = "player_id")
    private User user;

    @Column(name = "squad_status", nullable = false)
    private String squadStatus; // 'MNNIT_SQUAD', 'REGULAR_PRACTICE', etc.

    @Column(nullable = false)
    private String position;

    @Column(name = "preferred_foot")
    private String preferredFoot;

    @Column(name = "jersey_number")
    private Integer jerseyNumber;
}