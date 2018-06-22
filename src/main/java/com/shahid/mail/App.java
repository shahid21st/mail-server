package com.shahid.mail;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan({"com.shahid.mail"})
@EnableCircuitBreaker
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}