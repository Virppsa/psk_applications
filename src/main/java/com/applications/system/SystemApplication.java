package com.applications.system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@ImportResource("classpath:META-INF/beans.xml")
@EnableRetry
@EnableAsync
public class SystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(SystemApplication.class, args);
	}
}
