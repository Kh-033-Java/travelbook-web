package com.Kh033Java.travelbook.dto;

import com.Kh033Java.travelbook.entity.Description;

public class CountryGeneralInfoDTO {
	
	private final String name;
	private final Description description;
	private final Weather weather;
	
	public CountryGeneralInfoDTO(String name, Description description, Weather weather) {
		this.name = name;
		this.description = description;
		this.weather = weather;
	}
	
	

}
