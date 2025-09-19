package dev.kauan.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.kauan.api.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByNameAndPassword(String name, String password);
}
