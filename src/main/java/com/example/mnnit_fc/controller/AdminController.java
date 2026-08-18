package com.example.mnnit_fc.controller;


import com.example.mnnit_fc.controller.dto.*;
import com.example.mnnit_fc.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @PutMapping("/approve-request/{regNumber}")
    public ResponseEntity<ApproveResponseDTO> approvePlayer(@PathVariable String regNumber){

        ApproveResponseDTO responseDTO =adminService.approvePlayerRequest(
                new ApproveRequestDTO(regNumber)
        );

        return ResponseEntity.ok().body(responseDTO);
    }

    @PutMapping("/reject-request/{regNumber}")
    public ResponseEntity<RejectResponseDTO> rejectPlayer(@PathVariable String regNumber){
        RejectResponseDTO responseDTO = adminService.rejectPlayer(
                new RejectRequestDTO(regNumber)
        );

        return ResponseEntity.ok().body(responseDTO);
    }

}
