package com.frame4j;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableEurekaClient
@EnableZuulProxy
// @EnableZuulProxy 开启网关代理
public class Frame4jZuulApplication {

    public static void main(String[] args) {
        SpringApplication.run(Frame4jZuulApplication.class, args);
    }
}
