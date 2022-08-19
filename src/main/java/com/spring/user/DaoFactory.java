package com.spring.user;

public class DaoFactory {

    public UserDao userDao(){
        return new UserDao(new ConnectionMakerForA());
    }

    public AccountDao accountDao(){
        return new AccountDao(new ConnectionMakerForA());
    }

    public MessageDao messageDao(){
        return new MessageDao(getConnectorMaker());
    }

    private ConnectionMaker getConnectorMaker() {
        return new ConnectionMakerForA();
    }
}
