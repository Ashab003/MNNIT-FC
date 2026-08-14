package com.example.mnnit_fc.repository;

import com.example.mnnit_fc.entity.User;
import com.example.mnnit_fc.enums.Role;
import org.springframework.data.domain.Limit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    Optional<User> findByUserId(Long userId);

    @Query("SELECT u FROM User u WHERE u.email = :identifier OR CAST(u.userId AS string) = :identifier")
    Optional<User> findByEmailOrUserId(@Param("identifier") String identifier);

    long countByRole(Role role);
}
