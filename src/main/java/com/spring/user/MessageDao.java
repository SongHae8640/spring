package com.spring.user;

import java.sql.SQLException;

public class MessageDao {

    private final ConnectionMaker connectorMaker;

    public MessageDao(ConnectionMaker connectorMaker){
        this.connectorMaker = connectorMaker;
    }

    public void add(User user) throws ClassNotFoundException, SQLException {}

    public Message get(String id) throws ClassNotFoundException, SQLException {
        return null;
    }

    public void clear() throws ClassNotFoundException, SQLException {}
}
