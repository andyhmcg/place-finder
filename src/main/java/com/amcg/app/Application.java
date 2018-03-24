package com.amcg.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
@ComponentScan(basePackages = {"com.amcg.controller"})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }


}