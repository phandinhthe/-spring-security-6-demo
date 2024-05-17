package com.terry.security.demo.springsecurity6demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.terry.security.demo.springsecurity6demo")
public class SpringSecurity6DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurity6DemoApplication.class, args);
	}

}
