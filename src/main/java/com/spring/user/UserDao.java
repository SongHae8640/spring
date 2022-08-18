package com.spring.user;

import java.sql.*;

public class UserDao {

    private final ConnectionMaker connectorMaker;

    public UserDao(ConnectionMaker connectorMaker){
        this.connectorMaker = connectorMaker;
    }

    public void add(User user) throws ClassNotFoundException, SQLException {
        Connection conn = connectorMaker.makeConnection();

        PreparedStatement ps = conn.prepareStatement(
                "INSERT INTO USERS(id, name, password) VALUES (?,?,?)");
        ps.setString(1, user.getId());
        ps.setString(2, user.getName());
        ps.setString(3, user.getPassword());

        ps.executeUpdate();

        ps.close();
        conn.close();
    }

    public User get(String id) throws ClassNotFoundException, SQLException {
        Connection conn = connectorMaker.makeConnection();

        PreparedStatement ps = conn.prepareStatement(
                "SELECT * FROM users WHERE id  = ?");
        ps.setString(1, id);

        ResultSet rs = ps.executeQuery();
        rs.next();

        User user = new User();
        user.setId(rs.getString("id"));
        user.setName(rs.getString("name"));
        user.setPassword(rs.getString("password"));

        ps.close();
        conn.close();

        return user;
    }

    public void clear() throws ClassNotFoundException, SQLException {
        Connection conn = connectorMaker.makeConnection();

        PreparedStatement ps = conn.prepareStatement(
                "DELETE FROM USERS");

        ps.executeUpdate();

        ps.close();
        conn.close();
    }
}
