package com.Kh033Java.travelbook.dto;

import java.util.List;

import com.Kh033Java.travelbook.entity.Description;
import com.Kh033Java.travelbook.entity.Photo;

/**
 * 
 * @author Anatolii Melchenko
 *
 */
public class CountryGeneralInfoDTO {
	
	private final String name;
	private final Description description;
	private final WeatherDTO weather;
	private final List<Photo> photos;
	
	public CountryGeneralInfoDTO(String name, Description description, WeatherDTO weather, List<Photo> photos) {
		this.name = name;
		this.description = description;
		this.weather = weather;
		this.photos = photos;
	}	

}
