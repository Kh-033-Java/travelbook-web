package com.Kh033Java.travelbook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class TravelbookAplication {

	public static void main(String[] args) {
		SpringApplication.run(TravelbookAplication.class, args);
	}

}
