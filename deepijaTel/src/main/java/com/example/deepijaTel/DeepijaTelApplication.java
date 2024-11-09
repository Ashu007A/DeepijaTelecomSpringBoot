package com.example.deepijaTel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class DeepijaTelApplication {

	public static void main(String[] args) {
		SpringApplication.run(DeepijaTelApplication.class, args);
	}

}
