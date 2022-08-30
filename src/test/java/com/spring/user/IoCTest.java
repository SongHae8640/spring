package com.spring.user;

import org.h2.jdbc.JdbcSQLNonTransientException;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class IoCTest{

    @Test
    public void getApplicationContext() throws SQLException {
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
    public void userDaoCountingConnectionTest() throws SQLException {
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
    public void addAndGetTest() throws SQLException {
        ApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);
        UserDao userDao = context.getBean("userDao", UserDao.class);

        userDao.clear();
        assertEquals(0,userDao.getCount());

        User user = new User();
        user.setId("Song");
        user.setName("송");
        user.setPassword("송송");

        userDao.add(user);
        assertEquals(1, userDao.getCount());

        User savedUser = userDao.get(user.getId());
        assertEquals(user.getId(), savedUser.getId());
        assertEquals(user.getPassword(), savedUser.getPassword());

    }


    @Test
    public void userCount() throws SQLException{
        ApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);
        UserDao userDao = context.getBean("userDao", UserDao.class);

        userDao.clear();
        assertEquals(0,userDao.getCount());

        int userCount = 10;

        for (int i = 0; i < userCount; i++) {
            userDao.add(new User("id"+i, "name"+i, "pw"+i));
            assertEquals(i+1, userDao.getCount());
        }

    }

    @Test()
    public void getUserFailure() throws SQLException{
        ApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);
        UserDao userDao = context.getBean("userDao", UserDao.class);

        userDao.clear();
        assertEquals(0,userDao.getCount());

        assertThrows(JdbcSQLNonTransientException.class , () -> userDao.get("UNKNOWN_ID"));
    }
}
