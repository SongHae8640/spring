package com.spring.user;

import java.sql.Connection;
import java.sql.SQLException;

public class CountingConnectionMaker implements ConnectionMaker{
    int connectCount = 0;
    private final ConnectionMaker connectionMaker;

    public CountingConnectionMaker(ConnectionMaker connectionMaker){
        this.connectionMaker = connectionMaker;
    }

    @Override
    public Connection makeConnection() throws ClassNotFoundException, SQLException {
        connectCount++;
        return connectionMaker.makeConnection();
    }

    public int getConnectCount() {
        return connectCount;
    }
}
