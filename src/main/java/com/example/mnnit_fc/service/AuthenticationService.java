package com.example.mnnit_fc.service;

import com.example.mnnit_fc.controller.dto.LoginRequestDTO;
import com.example.mnnit_fc.controller.dto.LoginResponseDTO;
import com.example.mnnit_fc.entity.User;
import com.example.mnnit_fc.repository.UserRepository;
import com.example.mnnit_fc.security.CustomUserDetails;
import com.example.mnnit_fc.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public LoginResponseDTO login(LoginRequestDTO request) {

        // Authenticating the User
        // automatically hashes the incoming password and compares it to the database.
        // If it fails, Spring throws an exception here and stops execution.
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmailOrRegistrationNumber(),
                        request.getPassword()
                )
        );

        // 2. Fetch the User from the database
        User user = userRepository.findByEmailOrUserId(request.getEmailOrRegistrationNumber())
                .orElseThrow(() -> new RuntimeException("Invalid Credentials!!"));

        // 3. Wrap the database entity in our CustomUserDetails
        CustomUserDetails userDetails = new CustomUserDetails(user);

        // 4. Generate the JWT Token using the wrapped details
        String jwtToken = jwtService.generateToken(userDetails);

        // 5. Return the token and basic info back to the React frontend
        return new LoginResponseDTO(jwtToken, "welcome to mnnit-fc! " + user.getName() + " \n, you are logged in as " + user.getRole().name());
    }
}