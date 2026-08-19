package com.example.mnnit_fc.auth;

import com.example.mnnit_fc.auth.dto.LoginRequestDTO;
import com.example.mnnit_fc.auth.dto.LoginResponseDTO;
import com.example.mnnit_fc.auth.dto.RegisterUserRequestDTO;
import com.example.mnnit_fc.auth.dto.RegisterUserResponseDTO;
import com.example.mnnit_fc.player.entity.PlayerProfile;
import com.example.mnnit_fc.player.entity.User;
import com.example.mnnit_fc.core.enums.Role;
import com.example.mnnit_fc.core.enums.SquadStatus;
import com.example.mnnit_fc.player.repo.PlayerProfileRepository;
import com.example.mnnit_fc.player.repo.UserRepository;
import com.example.mnnit_fc.core.security.CustomUserDetails;
import com.example.mnnit_fc.core.security.JwtService;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final PlayerProfileRepository playerProfileRepository;
    public LoginResponseDTO login(LoginRequestDTO request) {

        //Authenticating the User
        //automatically hashes the incoming password and compares it to the database.
        //If it fails, Spring throws an exception here and stops execution.
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmailOrRegistrationNumber(),
                        request.getPassword()
                )
        );

        //Fetch the User from the database
        User user = userRepository.findByEmailOrUserId(request.getEmailOrRegistrationNumber())
                .orElseThrow(() -> new RuntimeException("Invalid Credentials!!"));

        //checking if user is approved or not if it is not then cant login
        if (!user.getIsApproved()) {
            // If they aren't approved, stop execution and throw an error!
            throw new RuntimeException("Your account is pending Super Admin approval. Please check back later!");
        }

        //Wrap the database entity in our CustomUserDetails
        CustomUserDetails userDetails = new CustomUserDetails(user);

        //Generate the JWT Token using the wrapped details
        String jwtToken = jwtService.generateToken(userDetails);

        //Return the token and basic info back to the React frontend
        return new LoginResponseDTO(jwtToken, "welcome to mnnit-fc! " + user.getName() + " \n, you are logged in as " + user.getRole().name());
    }

    @Transactional // 1. CRITICAL: Ensures both saves succeed or both rollback
    public RegisterUserResponseDTO registerUser(RegisterUserRequestDTO request) {

        if (userRepository.findByEmailOrUserId(request.getEmail()).isPresent() ||
                userRepository.findById(request.getRegistrationNumber()).isPresent()
        ) {
            throw new RuntimeException("User with this email or registration number already exists!");
        }

        // creating login credentials
        User user = new User();
        user.setUserId(request.getRegistrationNumber());
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setAcademicBranch(request.getAcademicBranch());
        user.setGraduationYear(request.getGraduationYear());
        user.setDateOfBirth(java.time.LocalDate.parse(request.getDateOfBirth()));
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        user.setRole(Role.ROLE_PLAYER);
        user.setIsApproved(false);

        userRepository.save(user);

        //checking for alumni

        //searching if the Admin already made a profile for this reg no.
        Optional<PlayerProfile> existingProfileOpt = playerProfileRepository
                .findByRegistrationNumber(request.getRegistrationNumber());

        if (existingProfileOpt.isPresent()) {
            // if alumni player profile is there link the user to profile

            PlayerProfile existingProfile = existingProfileOpt.get();
            existingProfile.setUser(user);//setting as user//
            existingProfile.setPosition(request.getPosition());
            existingProfile.setPreferredFoot(request.getPreferredFoot());
            existingProfile.setJerseyNumber(request.getJerseyNumber());

            playerProfileRepository.save(existingProfile);

        } else {

            //creating new profile user as the one who is signing up is not any alumni player
            PlayerProfile profile = new PlayerProfile();
            profile.setUser(user);

            // Set the permanent anchor so we can find them later if they ever delete their User account
            profile.setRegistrationNumber(request.getRegistrationNumber());

            profile.setPlayerName(user.getName());
            profile.setPosition(request.getPosition());
            profile.setPreferredFoot(request.getPreferredFoot());
            profile.setJerseyNumber(request.getJerseyNumber());
            profile.setSquadStatus(SquadStatus.TOURNAMENT_PLAYER);

            playerProfileRepository.save(profile);
        }

        return new RegisterUserResponseDTO(
                "Registered Successfully!!, now wait for the admin approval to log in!"
        );
    }
}