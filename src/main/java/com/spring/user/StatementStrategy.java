package com.spring.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface StatementStrategy{
    PreparedStatement makeStatement(Connection conn) throws SQLException;
}
