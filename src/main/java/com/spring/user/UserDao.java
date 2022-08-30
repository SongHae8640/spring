package com.spring.user;

import org.springframework.dao.EmptyResultDataAccessException;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {

    private final DataSource dataSource;

    public UserDao(DataSource dataSource){
        this.dataSource = dataSource;
    }

    public void add(User user) throws  SQLException {
        Connection conn = dataSource.getConnection();

        PreparedStatement ps = conn.prepareStatement(
                "INSERT INTO USERS(id, name, password) VALUES (?,?,?)");
        ps.setString(1, user.getId());
        ps.setString(2, user.getName());
        ps.setString(3, user.getPassword());

        ps.executeUpdate();

        ps.close();
        conn.close();
    }

    public User get(String id) throws SQLException {
        Connection conn = dataSource.getConnection();

        PreparedStatement ps = conn.prepareStatement(
                "SELECT * FROM users WHERE id  = ?");
        ps.setString(1, id);

        ResultSet rs = ps.executeQuery();
        User user = null;
        if(rs.next()){
            user = new User();
            user.setId(rs.getString("id"));
            user.setName(rs.getString("name"));
            user.setPassword(rs.getString("password"));

        }

        if(user == null) throw new EmptyResultDataAccessException(1);


        return user;
    }

    public void clear() throws SQLException {
        Connection conn = dataSource.getConnection();

        PreparedStatement ps = conn.prepareStatement(
                "DELETE FROM USERS");

        ps.executeUpdate();

        ps.close();
        conn.close();
    }

    public int getCount() throws SQLException{
        Connection conn = dataSource.getConnection();
        PreparedStatement ps = conn.prepareStatement("SELECT COUNT(1) FROM USERS");
        ResultSet rs = ps.executeQuery();
        rs.next();
        int count = rs.getInt(1);

        rs.close();
        ps.close();
        conn.close();

        return count;
    }
}
