package com.schooloftech.railways;

import com.schooloftech.railways.form.BookingForm;
import com.schooloftech.railways.service.BookingService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class RailwaysApplication {
	@Bean
	public BookingForm bookingForm(){
		return new BookingForm();
	}
	public static void main(String[] args) {
		SpringApplication.run(RailwaysApplication.class, args);
	}

}
