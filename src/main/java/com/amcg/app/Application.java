package com.amcg.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.ComponentScan;

@EnableHystrix
@SpringBootApplication
@ComponentScan(basePackages = {"com.amcg.controller","com.amcg.service","com.amcg.validation","com.amcg.exception","com.amcg.foursquare.client","com.amcg.config"})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }


}