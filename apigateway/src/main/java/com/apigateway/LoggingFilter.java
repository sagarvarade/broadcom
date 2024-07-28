package com.apigateway;

import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.util.Token;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import com.apigateway.URLS.CallsWithOtherServices;

import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;

@Component
public class LoggingFilter implements GlobalFilter {

	private final Logger logger = LoggerFactory.getLogger(LoggingFilter.class);
	@Autowired
	private Environment env;

	private String broadcom_communication_token;
	@PostConstruct
	private void postConstruct() {
		this.broadcom_communication_token = env.getProperty("broadcom_communication_token");
	}


	@Autowired
	private CallsWithOtherServices URLS;

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		logger.info("Path ,{} ", exchange.getRequest().getPath());
		String tokenForMicroCommunication= exchange.getRequest().getHeaders().get("broadcom_communication_token").get(0);

		logger.info("Token From Call ,{} ", tokenForMicroCommunication);
		logger.info("Token Set For All microservices Call ,{} ", broadcom_communication_token);

		if (!tokenForMicroCommunication.equals(broadcom_communication_token)) {
			throw new RuntimeException("You are not allow to communicate to this services {} " + tokenForMicroCommunication);
		}

		if (exchange.getRequest().getPath().toString().indexOf("auth/authenticate") > 0) {
			logger.info("Skipping this URL for authorization : ,{} ", exchange.getRequest().getPath());

			exchange.getRequest().mutate()
					.header("broadcom_communication_token", broadcom_communication_token)
					.build();

			return chain.filter(exchange);
		}
		String authHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
		String[] parts = authHeader.split(" ");
		System.out.println("Parts : " + Arrays.toString(parts));

		System.out.println("Bearer : "+parts[0]);
		System.out.println("Token :  "+parts[1]);
		Map<String, String> tokenParts=new HashMap<>();
		try {
			tokenParts=Token.getDecompressToken(parts[1]);
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
		System.out.println(" Mutating request:  with data "+tokenParts);
		exchange.getRequest().mutate()
				.header("user_id", String.valueOf(tokenParts.get("user_id")))
				.header("exp", String.valueOf(tokenParts.get("exp")))
				.header("iat", String.valueOf(tokenParts.get("iat")))
				.header("roles", String.valueOf(tokenParts.get("roles")))
				.header("sub", String.valueOf(tokenParts.get("sub")))
				.header("broadcom_communication_token", broadcom_communication_token)
				.build();
		System.out.println(" Userid : "+String.valueOf(tokenParts.get("user_id")));
		System.out.println("exp   :" +String.valueOf(tokenParts.get("exp")));
		System.out.println("iat   :" +String.valueOf(tokenParts.get("iat")));
		System.out.println("roles :" +String.valueOf(tokenParts.get("roles")));
		System.out.println("sub   :" +String.valueOf(tokenParts.get("sub")));

		if (parts.length != 2 || !"Bearer".equals(parts[0])) {
			throw new RuntimeException("Incorrect authorization structure");
		}

		int response = httpClient(parts[1]);
		System.out.println("Response from Auth " + response);
		if (response != 200) {
			throw new RuntimeException("Token is not valid " + response);
		}
		return chain.filter(exchange);
	}

	public int httpClient(String token) {
		try {
			HttpResponse<String> checkToken = URLS.checkToken(token);
			return checkToken.statusCode();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
}
