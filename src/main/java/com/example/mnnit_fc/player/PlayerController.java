package com.example.mnnit_fc.player;

import com.example.mnnit_fc.player.dto.PlayerDashboardResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api/player")
@RequiredArgsConstructor
public class PlayerController {

    private final PlayerService playerService;

    @GetMapping("/me")
    public ResponseEntity<?> getMyDashboard(Principal principal) {
        return ResponseEntity.ok(
                playerService.getCurrentPlayerStat(principal.getName())
        );
    }
}
