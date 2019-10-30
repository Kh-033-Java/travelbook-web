package com.Kh033Java.travelbook.dto;


public class Weather {
	
	private final String temperature;
    private final String description;
    
	public Weather(String temperature, String description) {
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
