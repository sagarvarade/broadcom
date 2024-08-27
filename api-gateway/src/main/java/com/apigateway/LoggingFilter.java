package com.apigateway;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import com.apigateway.URLS.CheckToken;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.util.Token;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

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
	CheckToken checkTokenWithAuth;

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
		logger.info("Parts  : {}" + Arrays.toString(parts));
		logger.info("Bearer : {} "+parts[0]);
		logger.info("Token  : {} "+parts[1]);
		Map<String, String> tokenParts=new HashMap<>();
		try {
			tokenParts=Token.getDecompressToken(parts[1]);
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
		System.out.println(" Mutating request:  with data "+tokenParts);
		exchange.getRequest().mutate()
				.header("user_id", String.valueOf(tokenParts.get("sub")))
				.header("exp", String.valueOf(tokenParts.get("exp")))
				.header("iat", String.valueOf(tokenParts.get("iat")))
				.header("roles", String.valueOf(tokenParts.get("roles")))
				.header("broadcom_communication_token", broadcom_communication_token)
				.build();
		logger.info("Userid: {}" ,String.valueOf(tokenParts.get("sub")));
		logger.info("exp   : {}" ,String.valueOf(tokenParts.get("exp")));
		logger.info("iat   : {}" ,String.valueOf(tokenParts.get("iat")));
		logger.info("roles : {}" ,String.valueOf(tokenParts.get("roles")));

		if (parts.length != 2 || !"Bearer".equals(parts[0])) {
			throw new RuntimeException("Incorrect authorization structure");
		}

		int response = checkTokenWithAuth.checkToken(parts[1]).getStatusCodeValue();
		logger.info("Response from Auth : {} " , response);
		if (response != 200) {
			throw new RuntimeException("Token is not valid " + response);
		}
		return chain.filter(exchange);
	}

}
