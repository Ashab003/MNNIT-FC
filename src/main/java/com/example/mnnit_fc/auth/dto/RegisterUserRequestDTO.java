package com.example.mnnit_fc.auth.dto;

import com.example.mnnit_fc.core.enums.AcademicBranch;
import com.example.mnnit_fc.core.enums.Position;
import com.example.mnnit_fc.core.enums.PreferredFoot;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class RegisterUserRequestDTO {

    @NotBlank( message = "Registration number is required")
    @Size( min = 8, message = "Registration number is cant be less than 8 characters")
    private String registrationNumber;

    @NotBlank( message = "Name is Required")
    private String name;

    @NotBlank(message = "Email is required")
    @Email(message = "Must be a valid email format")
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min = 8, message = "Password must be at least 8 characters long")
    private String password;

    @NotNull(message = "Academic branch is required")
    private AcademicBranch academicBranch;

    @NotBlank(message = "Phone number is required")
    private String phoneNumber;

    @NotBlank(message = "Please enter your date of birth")
    private String dateOfBirth;

    @NotNull(message = "Position is required")
    private Position position;

    @NotNull(message = "tell you preferred foot")
    private PreferredFoot preferredFoot;

    @NotNull(message = "Jersey number is required")
    private Integer jerseyNumber;

    @NotNull(message = "Graduation year is required")
    private Integer graduationYear;
}
