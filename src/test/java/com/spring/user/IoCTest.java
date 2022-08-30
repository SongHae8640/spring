package com.spring.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = DaoFactory.class)
@DirtiesContext
public class IoCTest{

    @Autowired
    private UserDao userDao;

    private User user;
    private User user2;

    @BeforeEach
    public void setUp(){
        this.user = new User("Song","송","송송");
        this.user2 =  new User("Song2","송2","송송2");

        System.out.println(userDao.toString());
        System.out.println(user.toString());

    }

    @Test
    public void getApplicationContext() throws SQLException {
        userDao.clear();

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
        userDao.clear();

        userDao.add(user);

        User savedUser = userDao.get(user.getId());
        assertEquals(user.getId(), savedUser.getId());

    }

    @Test
    public void addAndGetTest() throws SQLException {
        userDao.clear();
        assertEquals(0,userDao.getCount());

        userDao.add(user);
        assertEquals(1, userDao.getCount());

        User savedUser = userDao.get(user.getId());
        assertEquals(user.getId(), savedUser.getId());
        assertEquals(user.getPassword(), savedUser.getPassword());

    }


    @Test
    public void userCount() throws SQLException{
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
        userDao.clear();
        assertEquals(0,userDao.getCount());

        assertThrows(EmptyResultDataAccessException.class , () -> userDao.get("UNKNOWN_ID"));
    }
}
