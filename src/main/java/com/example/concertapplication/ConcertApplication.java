package com.example.concertapplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
public class ConcertApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConcertApplication.class, args);
    }

}
