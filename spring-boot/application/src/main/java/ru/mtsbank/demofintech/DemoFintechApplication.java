package ru.mtsbank.demofintech;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class DemoFintechApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoFintechApplication.class, args);
	}

}
