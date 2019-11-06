package com.Kh033Java.travelbook.dto;

/**
 * 
 * @author Anatolii Melchenko
 *
 */
public class WeatherDTO {
	
	private final String temperature;
    private final String description;
    
	public WeatherDTO(String temperature, String description) {
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
