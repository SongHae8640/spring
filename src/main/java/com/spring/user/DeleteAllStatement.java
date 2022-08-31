package com.spring.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteAllStatement implements StatementStrategy{
    @Override
    public PreparedStatement makeStatement(Connection conn) throws SQLException {
        return conn.prepareStatement("DELETE FROM USERS");
    }
}
