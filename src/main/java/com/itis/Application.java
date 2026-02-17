package com.itis;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import com.itis.config.AppConfig;
import com.itis.service.UserService;

public class Application {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        UserService userService = context.getBean(UserService.class);

        userService.register("damir");
        userService.findUser("damir");
        userService.rename(1L, "damsik");
        userService.unregister(1L);

        context.close();
    }
}