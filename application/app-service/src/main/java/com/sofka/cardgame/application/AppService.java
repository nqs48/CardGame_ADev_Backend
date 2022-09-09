package com.sofka.cardgame.application;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableReactiveMongoRepositories
public class AppService {

    public static void main(String[] args) {
        SpringApplication.run(AppService.class, args);
    }
}
