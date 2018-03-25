package com.amcg.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.amcg.controller","com.amcg.service","com.amcg.validation","com.amcg.exception","com.amcg.foursquare.client"})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }


}