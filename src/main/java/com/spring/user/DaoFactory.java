package com.spring.user;

public class DaoFactory {

    public UserDao userDao(){
        return new UserDao(new ConnectionMakerForA());
    }
}
