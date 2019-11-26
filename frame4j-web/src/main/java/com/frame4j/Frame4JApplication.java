package com.frame4j;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching	//开启二级缓存
public class Frame4JApplication {
	public static void main(String[] args) {
		SpringApplication.run(Frame4JApplication.class, args);
	}
}
