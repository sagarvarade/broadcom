package com.apigateway;

import java.net.http.HttpResponse;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import com.apigateway.URLS.CallsWithOtherServices;

import reactor.core.publisher.Mono;

@Component
public class LoggingFilter implements GlobalFilter {

	private Logger logger = LoggerFactory.getLogger(LoggingFilter.class);

	@Autowired
	private CallsWithOtherServices URLS;

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		logger.info("Path ,{} ", exchange.getRequest().getPath());

		if (exchange.getRequest().getPath().toString().indexOf("auth/authenticate") > 0) {
			logger.info("Skiping this URL for authorozation : ,{} ", exchange.getRequest().getPath());
			return chain.filter(exchange);
		}
		String authHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
		String[] parts = authHeader.split(" ");
		System.out.println("Parts : " + Arrays.toString(parts));
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
