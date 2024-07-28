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
		HashMap<String,String> myMap2 = new HashMap<String, String>();
		ObjectMapper objectMapper = new ObjectMapper();
		myMap2 = objectMapper.readValue(payload, HashMap.class);

		return myMap2;
	}
}

