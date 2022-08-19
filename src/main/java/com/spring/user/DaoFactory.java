package com.spring.user;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DaoFactory {

    @Bean
    public UserDao userDao(){
        return new UserDao(getConnectorMaker());
    }

    @Bean
    public AccountDao accountDao(){
        return new AccountDao(getConnectorMaker());
    }

    @Bean
    public MessageDao messageDao(){
        return new MessageDao(getConnectorMaker());
    }

    private ConnectionMaker getConnectorMaker() {
        return new ConnectionMakerForA();
    }
}
