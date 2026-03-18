package com.itis.dz2.service;

import com.itis.dz2.entity.UserEntity;
import com.itis.dz2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;

    @Autowired
    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public void registerUser(UserEntity user) {
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