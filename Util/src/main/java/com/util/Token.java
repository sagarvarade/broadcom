package com.util;

import Bean.TokenDetails;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Base64;
import java.util.HashMap;


public class Token {
	public static TokenDetails getDecompressToken(String token) throws JsonProcessingException {
		String[] chunks = token.split("\\.");
		Base64.Decoder decoder = Base64.getUrlDecoder();

		ObjectMapper objectMapper = new ObjectMapper();
		HashMap tokenParts =
				objectMapper.readValue(new String(decoder.decode(chunks[1])), HashMap.class);

		return TokenDetails.builder()
				.header(new String(decoder.decode(chunks[0])))
				.payLoad(new String(decoder.decode(chunks[1])))
				.userId(String.valueOf(tokenParts.get("sub")))
				.expiry(String.valueOf(tokenParts.get("exp")))
				.iat(String.valueOf(tokenParts.get("iat")))
				.roles(String.valueOf(tokenParts.get("roles")))
				.build();
	}
}

