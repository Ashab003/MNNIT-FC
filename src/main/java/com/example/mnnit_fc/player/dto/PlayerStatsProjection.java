package com.example.mnnit_fc.player.dto;

public interface PlayerStatsProjection {
    Integer getGoals();
    Integer getAssists();
    Integer getMatchesPlayed();
    Integer getYellowCards();
    Integer getRedCards();
    
    Integer getAttendance();
}
