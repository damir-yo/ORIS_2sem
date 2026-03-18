package com.itis.dz2;

import com.itis.dz2.entity.PostEntity;
import com.itis.dz2.entity.UserEntity;
import com.itis.dz2.entity.UserStatus;
import com.itis.dz2.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Dz2Application {

    public static void main(String[] args) {
        SpringApplication.run(Dz2Application.class, args);
    }

    @Bean
    public CommandLineRunner test(UserService userService) {
        return args -> {

            UserEntity user = new UserEntity();
            user.setName("damir");
            user.setStatus(UserStatus.ACTIVE);

            PostEntity post1 = new PostEntity();
            post1.setTitle("first post");
            post1.setUser(user);

            PostEntity post2 = new PostEntity();
            post2.setTitle("second post");
            post2.setUser(user);

            user.getPosts().add(post1);
            user.getPosts().add(post2);

            userService.registerUser(user);

            System.out.println("user saved");
        };
    }
}