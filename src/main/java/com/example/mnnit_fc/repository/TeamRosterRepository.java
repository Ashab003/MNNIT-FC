package com.example.mnnit_fc.repositories;

import com.example.mnnit_fc.entities.TeamRoster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface TeamRosterRepository extends JpaRepository<TeamRoster, Integer> {
}
