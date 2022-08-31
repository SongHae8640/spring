package com.spring.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private JdbcContext jdbcContext;

    public UserDao(DataSource dataSource){
        this.dataSource = dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void setJdbcContext(JdbcContext jdbcContext) {
        this.jdbcContext = jdbcContext;
    }

    public void add(User user) throws  SQLException {
        jdbcContext.workWithStatementStrategy(
            new StatementStrategy() {
                @Override
                public PreparedStatement makeStatement(Connection conn) throws SQLException {
                    PreparedStatement ps = conn.prepareStatement(
                            "INSERT INTO USERS(id, name, password) VALUES (?,?,?)");
                    ps.setString(1, user.getId());
                    ps.setString(2, user.getName());
                    ps.setString(3, user.getPassword());

                    return ps;
                }
            }
        );
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
        jdbcContext.workWithStatementStrategy(
            new StatementStrategy() {
                @Override
                public PreparedStatement makeStatement(Connection conn) throws SQLException {
                    return conn.prepareStatement("DELETE FROM USERS");
                }
            }
        );
    }

    public int getCount() throws SQLException{
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = dataSource.getConnection();
            ps = conn.prepareStatement("SELECT COUNT(1) FROM USERS");
            rs = ps.executeQuery();
            rs.next();
            return rs.getInt(1);
        }catch (SQLException e){
            throw e;
        }finally {
            if(rs != null){
                rs.close();
            }

            if(ps != null){
                ps.close();
            }

            if(conn != null){
                conn.close();
            }

        }
    }
}
