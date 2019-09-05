package com.rogerdavila.demoscheduler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class DemoschedulerApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoschedulerApplication.class, args);
	}

}
