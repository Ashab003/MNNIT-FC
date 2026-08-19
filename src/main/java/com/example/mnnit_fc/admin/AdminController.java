package com.example.mnnit_fc.admin;


import com.example.mnnit_fc.auth.dto.ApproveRequestDTO;
import com.example.mnnit_fc.auth.dto.ApproveResponseDTO;
import com.example.mnnit_fc.auth.dto.RejectRequestDTO;
import com.example.mnnit_fc.auth.dto.RejectResponseDTO;
import com.example.mnnit_fc.player.PlayerService;
import com.example.mnnit_fc.player.dto.PlayerDashboardResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;
    private final PlayerService playerService;

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

    @GetMapping("/player-stats/{regNumber}")
    public ResponseEntity<PlayerDashboardResponseDTO> getPlayerStats(@PathVariable String regNumber){
        // Passes the URL variable to your service
        PlayerDashboardResponseDTO response = playerService.getCurrentPlayerStat(regNumber);
        return ResponseEntity.ok().body(response);
    }

}
