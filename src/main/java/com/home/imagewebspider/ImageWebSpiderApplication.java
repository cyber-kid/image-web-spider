package com.home.imagewebspider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

@SpringBootApplication
@EnableRedisRepositories
public class ImageWebSpiderApplication {

	public static void main(String[] args) {
		SpringApplication.run(ImageWebSpiderApplication.class, args);
	}

}
