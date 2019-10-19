package com.Kh033Java.travelbook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Main application.
 */
@SpringBootApplication
public class TravelBookApplication {
    /**
     * Main method.
     *
     * @param args - some args
     */
    public static void main(final String[] args) {
        SpringApplication.run(TravelBookApplication.class, args);
    }
}
