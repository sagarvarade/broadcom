package com.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Base64;
import java.util.HashMap;


public class Token {
	public static HashMap<String, String> getDecompressToken(String token) throws JsonMappingException, JsonProcessingException {
		String[] chunks = token.split("\\.");
		Base64.Decoder decoder = Base64.getUrlDecoder();
		String header = new String(decoder.decode(chunks[0]));
		String payload = new String(decoder.decode(chunks[1]));
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.readValue(payload, HashMap.class);
	}
}

