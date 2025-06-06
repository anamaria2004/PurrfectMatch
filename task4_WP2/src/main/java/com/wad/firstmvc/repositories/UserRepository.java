package com.wad.firstmvc.repositories;

import com.wad.firstmvc.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    // Add custom query methods if needed
}
