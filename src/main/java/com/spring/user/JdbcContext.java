package com.spring.user;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JdbcContext {

    private DataSource dataSource;

    public JdbcContext(DataSource dataSource){
        this.dataSource = dataSource;
    }

    public void workWithStatementStrategy(StatementStrategy stmt) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = dataSource.getConnection();
            ps = stmt.makeStatement(conn);
            ps.executeUpdate();
        }catch (SQLException e){
            throw e;
        }finally {
            if(ps != null){
                ps.close();
            }

            if(conn != null){
                conn.close();
            }

        }
    }
}
