package com.spring.user;

import java.sql.SQLException;

public class AccountDao {

    private final ConnectionMaker connectorMaker;

    public AccountDao(ConnectionMaker connectorMaker){
        this.connectorMaker = connectorMaker;
    }

    public void add(User user) throws ClassNotFoundException, SQLException {}

    public Account get(String id) throws ClassNotFoundException, SQLException {
        return null;
    }

    public void clear() throws ClassNotFoundException, SQLException {}
}
