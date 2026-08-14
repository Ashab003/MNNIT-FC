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

    // 1. Gives the profile its own independent ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "profile_id")
    private Long profileId;

    // 2. The User link is now OPTIONAL (nullable = true).
    // Active players will have a User; Alumni will have this as null.
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = true)
    private User user;

    // 3. Added so alumni (who have no User account) still have a name!
    @Column(name = "player_name", nullable = false)
    private String playerName;

    @Column(name = "squad_status", nullable = false)
    private String squadStatus; // 'ALUMNI', 'MNNIT_SQUAD', etc.

    @Column(nullable = false)
    private String position;

    @Column(name = "preferred_foot")
    private String preferredFoot;

    @Column(name = "jersey_number")
    private Integer jerseyNumber;
}