package com.example.mnnit_fc.repository;

import com.example.mnnit_fc.entity.User;
import com.example.mnnit_fc.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    long countByRole(Role role);
}
