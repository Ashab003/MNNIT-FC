package com.example.mnnit_fc.entity;

import com.example.mnnit_fc.enums.AcademicBranch;
import com.example.mnnit_fc.enums.Role;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @Column(name = "user_id", updatable = false, nullable = false)
    private String userId; // Set manually (Registration Number)

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
    private Role role;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Enumerated(EnumType.STRING)
    @Column(name = "academic_branch", nullable = false)
    private AcademicBranch academicBranch;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private PlayerProfile playerProfile;

    @Column(name = "is_approved", nullable = false)
    private Boolean isApproved = false; // Defaults to false for new signups

    @Column(name = "graduation_year", nullable = false)
    private Integer graduationYear;
}