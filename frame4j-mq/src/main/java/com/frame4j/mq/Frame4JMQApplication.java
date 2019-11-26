package com.frame4j.mq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableEurekaClient
public class Frame4JMQApplication {
    public static void main(String[] args) {
        SpringApplication.run(Frame4JMQApplication.class, args);
    }
    // 解决RestTemplate 找不到原因 应该把restTemplate注册SpringBoot容器中 @bean
    @Bean
    @LoadBalanced
    RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
