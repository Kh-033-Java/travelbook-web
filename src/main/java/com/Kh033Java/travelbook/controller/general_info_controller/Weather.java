package com.Kh033Java.travelbook.controller.general_info_controller;

import org.springframework.context.annotation.ComponentScan;

@ComponentScan
public class Weather {
	
	private final String temperature;
    private final String description;
    
	public Weather(String temperature, String description) {
		super();
		this.temperature = temperature;
		this.description = description;
	}

	public String getTemperature() {
		return temperature;
	}

	public String getDescription() {
		return description;
	}    
	
}
