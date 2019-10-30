package com.Kh033Java.travelbook.util;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class JsonConverter {
	
	public static String convertToJson(Object object) {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		return gson.toJson(object);
	}
	
	public static Map<String, Object> convertJsonToMap(String str) {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		return gson.fromJson(str, new TypeToken<HashMap<String, Object>>() {
		}.getType());
	}

}
