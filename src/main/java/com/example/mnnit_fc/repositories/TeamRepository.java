package com.example.mnnit_fc.repositories;

import com.example.mnnit_fc.entities.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface TeamRepository extends JpaRepository<Team, Long> {
}
