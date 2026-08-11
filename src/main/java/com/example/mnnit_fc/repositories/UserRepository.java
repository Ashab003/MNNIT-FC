package com.example.mnnit_fc.repositories;

import com.example.mnnit_fc.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
