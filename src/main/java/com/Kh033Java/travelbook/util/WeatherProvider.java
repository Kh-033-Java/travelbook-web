package com.Kh033Java.travelbook.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author Anatolii Melchenko
 *
 */

public class WeatherProvider {

	private static final String API_KEY = "3939f49317f69e576a49d2c1ef25356b";
	private static final String API_VERSION = "2.5";
	private Map<String, Object> weatherData;
	private static final Logger LOGGER = LoggerFactory.getLogger(WeatherProvider.class);

	public WeatherProvider(String location) {		
		weatherData = generateWeatherData(location);
	}

	public String getWeatherDescription() {
		List<Map<String, Object>> weatherGeneralInfo = (List<Map<String, Object>>) weatherData.get("weather");
		return weatherGeneralInfo.get(0).get("description").toString();
	}

	public String getTemperature() {
		Map<String, Object> weatherMain = JsonConverter.convertJsonToMap(weatherData.get("main").toString());
		return (convertToCelcium(weatherMain.get("temp")) + " \u00b0" + "C");
	}

	public String getTimeZone() {
		return convertToUTC(weatherData.get("timezone"));
	}

	private Map<String, Object> generateWeatherData(String location) {
		final String URL_STRING = "http://api.openweathermap.org/data/" + API_VERSION + "/weather?q=" + location + "&APPID=" + API_KEY;
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
		} catch (IOException e) {
			LOGGER.error("Could not parse result info from weather API");
		}
		return JsonConverter.convertJsonToMap(result.toString());	
	}

	private static String convertToCelcium(Object object) {
		Integer value = (int) Math.round(((Double) object - 273));
		return value.toString();
	}

	private static String convertToUTC(Object object) {
		double value = ((Double) object) / 3600;
		int intValue = (int) value;
		String UTC = "";
		if (value > 0) {
			UTC = "+" + intValue;
		} else if (value <= 0) {
			UTC = "" + intValue;
		}
		return UTC;
	}

}