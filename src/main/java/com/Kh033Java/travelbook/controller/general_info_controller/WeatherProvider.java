package com.Kh033Java.travelbook.controller.general_info_controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.*;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class WeatherProvider {
	
	private static final String API_KEY = "3939f49317f69e576a49d2c1ef25356b";
	private final Map<String, Object> weatherData;

	public WeatherProvider(String location) {
		super();
		weatherData = generateWeatherData(location);
	}
	
	public String getWeatherDescription() {
		List<Map<String, Object>> weatherGeneralInfo = (List<Map<String, Object>>) weatherData.get("weather");
		return weatherGeneralInfo.get(0).get("description").toString();
	}
	
	public String getTemperature() {
		Map<String, Object> weatherMain = jsonToMap(weatherData.get("main").toString());
		return (convertToCelcium(weatherMain.get("temp")) + " \u00b0" + "C");
	}
	
	public String getHumidity() {
		Map<String, Object> weatherMain = jsonToMap(weatherData.get("main").toString());
		return (Math.round((double) weatherMain.get("humidity")) + "%");
	}
	
	public String getWindSpeed() {
		Map<String, Object> weatherWind = jsonToMap(weatherData.get("wind").toString());
		return (weatherWind.get("speed") + " m/sec");
	}
	
	public String getTimeZone() {
		return convertToUTC(weatherData.get("timezone"));
	}
	
	private Map<String, Object> generateWeatherData(String location) {
		final String URL_STRING = "http://api.openweathermap.org/data/2.5/weather?q=" + location + "&APPID=" + API_KEY;
		StringBuilder result = new StringBuilder();
		try {
			URL url = new URL(URL_STRING);
			URLConnection connection = url.openConnection();
			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String line;
			while ((line = reader.readLine()) != null) {
				result.append(line);
			}
			reader.close();
			System.out.println(result + "\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return jsonToMap(result.toString());
	}	
	
	
	

	public static Map<String, Object> jsonToMap(String str) {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		Map<String, Object> map = gson.fromJson(str, new TypeToken<HashMap<String, Object>>() {
		}.getType());
		return map;
	}

	@Deprecated
	public static List<Map<String, Object>> jsonToObject(String str) {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		List<Map<String, Object>> list = gson.fromJson(str, new TypeToken<HashMap<String, Object>[]>() {
		}.getType());
		return list;
	}

	private static String convertToCelcium(Object object) {
		double value = (Double) object - 273;
		DecimalFormat f = new DecimalFormat("##.0");
		return f.format(value);
	}

	private static String convertToUTC(Object object) {
		double value = ((Double) object) / 3600;
		int intValue = (int) value;
		String UTC = "";
		if (value > 0) {
			UTC = "+" + intValue;
		}
		else if (value <= 0) {
			UTC = "" + intValue;			
		}
		return UTC;
	}

}