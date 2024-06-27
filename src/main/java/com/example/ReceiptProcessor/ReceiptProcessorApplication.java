package com.example.ReceiptProcessor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
public class ReceiptProcessorApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReceiptProcessorApplication.class, args);
	}
}
