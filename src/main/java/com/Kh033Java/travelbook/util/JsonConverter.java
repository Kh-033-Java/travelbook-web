package com.Kh033Java.travelbook.util;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

/**
 * 
 * @author Anatolii Melchenko
 *
 */
public class JsonConverter {
	
	private JsonConverter() {
		
	}
	
	public static String convertToJson(Object object) {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		return gson.toJson(object);
	}
	
	public static Map<String, Object> convertJsonToMap(String str) {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		Map<String, Object> map = gson.fromJson(str, new TypeToken<HashMap<String, Object>>() {
		}.getType());
		return map;
	}

}
