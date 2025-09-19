package dev.kauan.api.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import dev.kauan.api.repository.UserRepository;

@Service
@Transactional
public class AuthService {
    @Autowired
    private UserRepository userRepository;

    @Transactional(readOnly = true)
    public Optional<String> getTokenFromCredentials(String name, String password) {
        return userRepository.findByNameAndPassword(name, password).map(user -> user.getName() + "_token");
    }
}
