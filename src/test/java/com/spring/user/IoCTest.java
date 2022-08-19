package com.spring.user;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class IoCTest {

    @Test
    public void getApplicationContext() throws SQLException, ClassNotFoundException {
        ApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);
        UserDao userDao = context.getBean("userDao", UserDao.class);

        userDao.clear();

        User user = new User();
        user.setId("Song");
        user.setName("송");
        user.setPassword("송송");

        userDao.add(user);

        User savedUser = userDao.get(user.getId());
        assertEquals(user.getId(), savedUser.getId());


    }
}
