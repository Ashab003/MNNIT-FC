package com.example.mnnit_fc.player;

import com.example.mnnit_fc.match.repo.MatchRepository;
import com.example.mnnit_fc.match.repo.MatchStatRepository;
import com.example.mnnit_fc.player.dto.PlayerDashboardResponseDTO;
import com.example.mnnit_fc.player.dto.PlayerStatsProjection;
import com.example.mnnit_fc.player.entity.PlayerProfile;
import com.example.mnnit_fc.player.entity.User;
import com.example.mnnit_fc.player.repo.PlayerProfileRepository;
import com.example.mnnit_fc.player.repo.UserRepository;
import com.example.mnnit_fc.practice.PracticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PlayerService {
    private final PlayerProfileRepository playerProfileRepository;
    private final UserRepository userRepository;
    private final MatchStatRepository matchStatRepository;
    private final PracticeService practiceService;

    public PlayerDashboardResponseDTO getCurrentPlayerStat(String id){
        User user = userRepository.findByEmailOrUserId(id).orElseThrow(
                ()-> new RuntimeException("Player not found with this registration number : " + id)
        );

        PlayerProfile playerProfile = user.getPlayerProfile();
        //making sure to pass reg no because principle is used in controller so it can give email or reg no
        String reg_no = user.getUserId();
        //getting the stats
        PlayerStatsProjection stats = matchStatRepository.getPlayerStats(reg_no);

        //initializing everything to zero incase nothing is there
        int totalGoals = 0;
        int totalAssists = 0;
        int matchesPlayed = 0;
        int totalYellowCards = 0;
        int totalRedCards = 0;

        //extracting values only if stats actually exists
        if (stats != null) {
            totalGoals = stats.getGoals() != null ? stats.getGoals() : 0;
            totalAssists = stats.getAssists() != null ? stats.getAssists() : 0;
            matchesPlayed = stats.getMatchesPlayed() != null ? stats.getMatchesPlayed() : 0;
            totalYellowCards = stats.getYellowCards() != null ? stats.getYellowCards() : 0;
            totalRedCards = stats.getRedCards() != null ? stats.getRedCards() : 0;
        }

        // 4. Build the DTO
        PlayerDashboardResponseDTO responseDTO = PlayerDashboardResponseDTO.builder()
                .goals(totalGoals)
                .assists(totalAssists)
                .matchesPlayed(matchesPlayed)
                .yellowCards(totalYellowCards)
                .redCards(totalRedCards)
                .name(user.getName())
                .academicBranch(String.valueOf(user.getAcademicBranch()))
                .squadStatus(String.valueOf(playerProfile.getSquadStatus()))
                .position(String.valueOf(playerProfile.getPosition()))
                .jerseyNumber(playerProfile.getJerseyNumber())
                .preferredFoot(String.valueOf(playerProfile.getPreferredFoot()))
                .trainingAttendancePercentage(practiceService.practicePercentage(playerProfile.getProfileId()))
                .build();

        return responseDTO;
    }
}
