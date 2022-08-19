package com.spring.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserDaoTest {

    @BeforeEach
    public void clearAllUser() throws SQLException, ClassNotFoundException {
        UserDao userDao = new DaoFactory().userDao();
        userDao.clear();
    }

    @Test
    public void addTest() throws SQLException, ClassNotFoundException {
        User user = new User();
        user.setId("Song");
        user.setName("송");
        user.setPassword("송송");

        UserDao userDao = new DaoFactory().userDao();
        userDao.add(user);

        User savedUser = userDao.get(user.getId());
        assertEquals(user.getId(), savedUser.getId());
    }

}