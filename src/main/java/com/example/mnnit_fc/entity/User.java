package com.example.mnnit_fc.entities;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @Column(name = "user_id", updatable = false, nullable = false)
    private Long userId; // Set manually (Registration Number)

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(nullable = false)
    private String password; // Encrypted BCrypt hash for Spring Security

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private String role; // e.g., 'ROLE_ADMIN', 'ROLE_PLAYER'

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Column(name = "academic_branch")
    private String academicBranch;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private PlayerProfile playerProfile;

    @Column(name = "is_approved", nullable = false)
    private Boolean isApproved = false; // Defaults to false for new signups
}