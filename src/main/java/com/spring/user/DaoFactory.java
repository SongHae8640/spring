package com.spring.user;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DaoFactory {

    @Bean
    public UserDao userDao(){
        return new UserDao(connectionMaker());
    }

    @Bean
    public ConnectionMaker realConnectorMaker() {
        return new ConnectionMakerForA();
    }

    @Bean
    public ConnectionMaker connectionMaker(){
        return new CountingConnectionMaker(realConnectorMaker());
    }

    @Bean
    public AccountDao accountDao(){
        return new AccountDao(connectionMaker());
    }

    @Bean
    public MessageDao messageDao(){
        return new MessageDao(connectionMaker());
    }
}
