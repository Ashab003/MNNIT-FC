package com.example.mnnit_fc.service;

import com.example.mnnit_fc.controller.dto.FullNameResponseDTO;
import com.example.mnnit_fc.entity.User;
import com.example.mnnit_fc.repository.UserRepository;
import com.example.mnnit_fc.security.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User getUserById(String id){
        return userRepository.findById(id).orElseThrow(
                ()-> new RuntimeException("User not found by registration number : " + id)
        );
    }

    public User getUserByEmail(String email){
        return userRepository.findByEmail(email).orElseThrow(
                ()-> new RuntimeException("User not found by email : " + email)
        );
    }

    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder
                .getContext()
                .getAuthentication();

        //"Make sure the user is logged in,
        // and if their security profile is a CustomUserDetails object,
        // grab it, cast it,
        // and hand it to me in a variable called userDetails."
        if (authentication != null && authentication.getPrincipal() instanceof CustomUserDetails userDetails) {
            return userDetails.getUser();
        }

        throw new RuntimeException("User Not Authenticated");
    }

    public User getUserByEmailOrRegistrationNumber(String emailOrRegistrationNumber){
        return userRepository.findByEmailOrUserId(emailOrRegistrationNumber).orElseThrow(
               ()-> new RuntimeException("User not found by the given credentials : " + emailOrRegistrationNumber)
        );
    }

    public String getCurrentUserId() {
        return getCurrentUser().getUserId();
    }

    public FullNameResponseDTO getUserFullName() {
        return new FullNameResponseDTO(
                getCurrentUser().getName()
        );
    }
}
