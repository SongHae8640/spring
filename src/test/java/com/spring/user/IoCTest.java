package com.spring.user;

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

    @Test
    public void compareBeans(){
        ApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);
        UserDao userDao1 = context.getBean("userDao", UserDao.class);
        UserDao userDao2 = context.getBean("userDao", UserDao.class);

        assertEquals(userDao1, userDao2);

        assertEquals(userDao1.toString(), userDao2.toString());
        System.out.println(userDao1);
        System.out.println(userDao2);


    }

    @Test
    public void userDaoCountingConnectionTest() throws SQLException, ClassNotFoundException {
        ApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);
        UserDao userDao = context.getBean("userDao", UserDao.class);
        CountingConnectionMaker connectionMaker = context.getBean("connectionMaker", CountingConnectionMaker.class);

        userDao.clear();

        User user = new User();
        user.setId("Song");
        user.setName("송");
        user.setPassword("송송");

        userDao.add(user);

        User savedUser = userDao.get(user.getId());
        assertEquals(user.getId(), savedUser.getId());
        assertEquals(3, connectionMaker.getConnectCount());

    }
}
