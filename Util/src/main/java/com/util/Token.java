package com.util;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;


public class Token {
	public static Map<String, String> getDecompressToken(String token) throws JsonMappingException, JsonProcessingException {
		String[] chunks = token.split("\\.");
		Base64.Decoder decoder = Base64.getUrlDecoder();

		String header = new String(decoder.decode(chunks[0]));
		String payload = new String(decoder.decode(chunks[1]));
		//System.out.println("Header :"+header);
		//System.out.println("payload :"+payload);
		Map<String,String> myMap = new HashMap<String, String>();
		Map<String,String> myMap2 = new HashMap<String, String>();
		ObjectMapper objectMapper = new ObjectMapper();
		myMap = objectMapper.readValue(header, HashMap.class);
		myMap2 = objectMapper.readValue(payload, HashMap.class);
		//System.out.println("Map is: "+myMap);
		//System.out.println("Map is: "+myMap2);
		
		return myMap2;
	}
}

