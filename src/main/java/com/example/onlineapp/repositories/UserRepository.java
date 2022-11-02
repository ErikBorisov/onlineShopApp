package com.example.onlineapp.repositories;

import com.example.onlineapp.models.User;
import com.example.onlineapp.models.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findUserByRoleIs(Role role);

    boolean existsByEmail(String email);

    Optional<User> findByActivationToken(String token);

    Optional<User> findByEmail(String email);
}
