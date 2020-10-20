package com.example.doctormamaassistance.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class DoctorMamaAssistanceApplication {
	public static void main(String[] args) {
		SpringApplication.run(DoctorMamaAssistanceApplication.class, args);
	}
}