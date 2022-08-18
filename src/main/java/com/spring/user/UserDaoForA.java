package com.spring.user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class UserDaoForA extends UserDao {
    @Override
    Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("org.h2.Driver");
        Connection conn = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test", "sa", "");
        return conn;
    }
}
