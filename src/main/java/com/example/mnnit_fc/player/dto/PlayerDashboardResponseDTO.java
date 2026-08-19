package com.example.mnnit_fc.player.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PlayerDashboardResponseDTO {

    //Identity
    private String name;
    private String academicBranch;
    private String squadStatus;
    private String position;
    private Integer jerseyNumber;
    private String preferredFoot;

    //Performance Stats
    private int matchesPlayed;
    private int goals;
    private int assists;
    private int yellowCards;
    private int redCards;

    //practice stuff
    private double trainingAttendancePercentage;
}