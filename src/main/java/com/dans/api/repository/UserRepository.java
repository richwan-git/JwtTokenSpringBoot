package com.dans.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dans.api.models.entities.Users;

public interface UserRepository extends JpaRepository<Users, Long> {
    public Optional<Users> findByUsername(String username);

    public Optional<Users> findByUsernameAndPassword(String username, String password);
}
