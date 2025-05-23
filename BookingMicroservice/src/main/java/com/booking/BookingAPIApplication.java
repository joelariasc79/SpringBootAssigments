package com.booking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing // Enable JPA auditing
public class BookingAPIApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookingAPIApplication.class, args);
	}

}
