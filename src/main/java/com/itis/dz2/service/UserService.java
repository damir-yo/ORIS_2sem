package com.itis.dz2.service;

import com.itis.dz2.entity.UserEntity;
import com.itis.dz2.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    public UserService(
            UserRepository repository,
            PasswordEncoder passwordEncoder
    ) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public void registerUser(UserEntity user) {

        user.setPassword(
                passwordEncoder.encode(user.getPassword())
        );

        repository.save(user);
    }

    public UserEntity findUser(Long id) {
        return repository.findById(id);
    }

    public void updateUser(UserEntity user) {
        repository.update(user);
    }

    public void removeUser(Long id) {
        repository.delete(id);
    }
}