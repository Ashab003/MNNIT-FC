package com.example.mnnit_fc.auth.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class LoginRequestDTO {

    @NotBlank(message = "Enter valid Email or Registration Number")
    private String emailOrRegistrationNumber;

    @NotBlank(message = " Password is required")
    @Size( min = 8, message = "Password must be at least 8 characters long")
    private String password;
}
