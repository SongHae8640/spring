package com.spring.user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class UserDaoForB extends UserDao {
    @Override
    Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("");
        Connection conn = DriverManager.getConnection("", "", "");
        return conn;
    }
}
