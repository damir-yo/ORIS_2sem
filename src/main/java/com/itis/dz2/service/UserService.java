package com.itis.dz2.service;

import com.itis.dz2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository repository;

    @Autowired
    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public void register(String name) {
        try {
            repository.add(name);
            System.out.println("user " + name + " successfully registered");
        } catch (DataAccessException e) {
            System.err.println("error register user " + e.getMessage());
        }
    }

    public void rename(Long id, String name) {
        try {
            repository.modify(id, name);
            System.out.println("username with id " + id + " edited to " + name);
        } catch (DataAccessException e) {
            System.err.println("error with editing name " + e.getMessage());
        }
    }

    public void unregister(Long id) {
        try {
            repository.remove(id);
            System.out.println("user with id " + id + " deleted");
        } catch (DataAccessException e) {
            System.err.println("error while deleting " + e.getMessage());
        }
    }

    public void findUser(String name) {
        try {
            var user = repository.getOne(name);

            if (user != null) {
                System.out.println("user was found " + user.getName());
            } else {
                System.out.println("user with name " + name + " was not found");
            }

        } catch (DataAccessException e) {
            System.err.println("error while finding user " + e.getMessage());
        }
    }
}