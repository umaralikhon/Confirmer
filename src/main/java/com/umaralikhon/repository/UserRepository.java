package com.umaralikhon.repository;

import com.umaralikhon.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);
    Optional<User> findByStCode(String code);
    Optional<User> findByBtCode(String code);
    Optional<User> findByEmailAndStCode(String email, String code);
}
