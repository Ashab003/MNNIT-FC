package com.example.mnnit_fc.service;

import com.example.mnnit_fc.controller.dto.ApproveRequestDTO;
import com.example.mnnit_fc.controller.dto.ApproveResponseDTO;
import com.example.mnnit_fc.controller.dto.RejectRequestDTO;
import com.example.mnnit_fc.controller.dto.RejectResponseDTO;
import com.example.mnnit_fc.entity.User;
import com.example.mnnit_fc.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final UserRepository userRepository;
    private final UserService userService;

    @Transactional
    public ApproveResponseDTO approvePlayerRequest(ApproveRequestDTO approveRequestDTO){
        //Find the user
        String regNo = approveRequestDTO.getRegistrationNumber();
        User user = userRepository.findById(regNo)
                .orElseThrow(() -> new RuntimeException("User not found with Registration Number: " + regNo));
        String name = user.getName();

        //mark the player as approved
        user.setIsApproved(true);
        userRepository.save(user);

        return new ApproveResponseDTO(
                "Player request for " + regNo + " and name: " + name + " has been APPROVED"
        );
    }
    @Transactional
    public RejectResponseDTO rejectPlayer(RejectRequestDTO  rejectRequestDTO) {
        //Find the user
        String regNo = rejectRequestDTO.getRegistrationNumber();
        User user = userRepository.findById(regNo)
                .orElseThrow(() -> new RuntimeException("User not found with Registration Number: " + regNo));
        String name = user.getName();
        //delete user from db
        userRepository.delete(user);

        return new RejectResponseDTO(
                "Player with registration number: " + regNo + " and name: " + name + "has been rejected"
        );
    }
}
