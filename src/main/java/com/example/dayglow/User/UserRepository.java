package com.example.dayglow.User;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<SiteUser, Long> {
    SiteUser findByUsername(String username);
    Optional<SiteUser> findByEmail(String email);
    Optional<SiteUser> findByUsernameAndEmail(String username, String email);
}


