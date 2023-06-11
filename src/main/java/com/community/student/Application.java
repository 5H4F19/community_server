package com.community.student;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@SpringBootApplication
@EnableMongoAuditing
public class Application {

	public static void main(String[] args) {
		System.out.println(System.getProperty("server.port"));
		SpringApplication.run(Application.class, args);
	}

}
