package com.example.mnnit_fc.admin;

import com.example.mnnit_fc.auth.dto.ApproveRequestDTO;
import com.example.mnnit_fc.auth.dto.ApproveResponseDTO;
import com.example.mnnit_fc.auth.dto.RejectRequestDTO;
import com.example.mnnit_fc.auth.dto.RejectResponseDTO;
import com.example.mnnit_fc.player.entity.User;
import com.example.mnnit_fc.player.repo.UserRepository;
import com.example.mnnit_fc.player.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
