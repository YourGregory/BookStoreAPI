package com.example.bookstoreapi.repository;

import com.example.bookstoreapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthRepository extends JpaRepository<User, Long> {

    Boolean existsByName(String name);

    Boolean existsByEmail(String email);

    Optional<User> findByName(String name);

    Optional<User> findByEmail(String email);
}
