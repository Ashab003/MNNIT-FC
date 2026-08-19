package com.example.mnnit_fc.player.entity;

import com.example.mnnit_fc.core.enums.Position;
import com.example.mnnit_fc.core.enums.PreferredFoot;
import com.example.mnnit_fc.core.enums.SquadStatus;
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

    @Column(name = "registration_number", unique = true, nullable = false)
    private String registrationNumber;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "squad_status", nullable = false)
    private SquadStatus squadStatus; // 'ALUMNI', 'MNNIT_SQUAD', etc.

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Position position;

    @Enumerated(EnumType.STRING)
    @Column(name = "preferred_foot", nullable = false)
    private PreferredFoot preferredFoot;

    @Column(name = "jersey_number")
    private Integer jerseyNumber;

}