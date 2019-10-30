package com.Kh033Java.travelbook.dto;

import com.Kh033Java.travelbook.entity.Description;

/**
 * 
 * @author Anatolii Melchenko
 *
 */
public class CountryGeneralInfoDTO {
	
	private final String name;
	private final Description description;
	private final WeatherDTO weather;
	
	public CountryGeneralInfoDTO(String name, Description description, WeatherDTO weather) {
		this.name = name;
		this.description = description;
		this.weather = weather;
	}
	
	

}
