package com.frame4j;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class Frame4JEurekaApplication {
    public static void main(String[] args) {
        SpringApplication.run(Frame4JEurekaApplication.class, args);
    }
}
