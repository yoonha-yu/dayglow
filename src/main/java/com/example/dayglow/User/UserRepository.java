package com.example.dayglow.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<SiteUser, Long> {
    SiteUser findByUsername(String username);
}
