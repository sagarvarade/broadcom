package com.apigateway;

import com.apigateway.clients.CheckToken;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.util.Token;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Component
public class AuthenticationFilter implements GlobalFilter, Ordered {

	private final Logger logger = LoggerFactory.getLogger(AuthenticationFilter.class);

	@Value("${broadcom_communication_token}")
	String broadcom_communication_token;

	@Autowired
	CheckToken checkTokenWithAuth;

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		try {
			logger.info("Path ,{} ", exchange.getRequest().getPath());
			String tokenForMicroCommunication = Objects.requireNonNull(exchange.getRequest().getHeaders().get("broadcom_communication_token")).get(0);

			logger.info("Token From Call ,{} ", tokenForMicroCommunication);
			logger.info("Token Set For All microservices Call ,{} ", this.broadcom_communication_token);

			if (!tokenForMicroCommunication.equals(this.broadcom_communication_token)) {
				throw new RuntimeException("You are not allow to communicate to this services {} " + tokenForMicroCommunication);
			}

			if (exchange.getRequest().getPath().toString().indexOf("auth/authenticate") > 0) {
				logger.info("Skipping this URL for authorization : ,{} ", exchange.getRequest().getPath());

				exchange.getRequest().mutate()
						.header("broadcom_communication_token", this.broadcom_communication_token)
						.build();

				return chain.filter(exchange);
			}
			String authHeader = Objects.requireNonNull(exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION)).get(0);
			String[] parts = authHeader.split(" ");
			logger.info("Parts  : {}" + Arrays.toString(parts));
			logger.info("Bearer : {} " + parts[0]);
			logger.info("Token  : {} " + parts[1]);

			if (parts.length != 2 || !"Bearer".equals(parts[0])) {
				throw new RuntimeException("Incorrect authorization structure");
			}

			int response = checkTokenWithAuth.checkToken(parts[1]).getStatusCodeValue();
			logger.info("Response from Auth : {} ", response);
			if (response != 200) {
				throw new RuntimeException("Token is not valid " + response);
			}
			Map<String, String> tokenParts = null;
			try {
				tokenParts = Token.getDecompressToken(parts[1]);
			} catch (JsonProcessingException e) {
				throw new RuntimeException(e);
			}
			System.out.println(" Mutating request:  with data " + tokenParts);
			exchange.getRequest().mutate()
					.header("user_id", String.valueOf(tokenParts.get("sub")))
					.header("exp", String.valueOf(tokenParts.get("exp")))
					.header("iat", String.valueOf(tokenParts.get("iat")))
					.header("roles", String.valueOf(tokenParts.get("roles")))
					.header("broadcom_communication_token", this.broadcom_communication_token)
					.build();
			logger.info("Userid: {}", tokenParts.get("sub"));
			logger.info("exp   : {}", tokenParts.get("exp"));
			logger.info("iat   : {}", tokenParts.get("iat"));
			logger.info("roles : {}", tokenParts.get("roles"));

			return chain.filter(exchange);
		}
		catch(Exception e){
			return handleException(exchange, e);
		}
	}
	private Mono<Void> handleException(ServerWebExchange exchange, Throwable e) {
		Map<String, Object> errorResponse = new HashMap<>();
		errorResponse.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
		errorResponse.put("error", "Internal Server Error");
		errorResponse.put("message", e.getMessage());

		byte[] bytes = errorResponse.toString().getBytes(StandardCharsets.UTF_8);
		exchange.getResponse().setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
		exchange.getResponse().getHeaders().setContentType(MediaType.APPLICATION_JSON);
		return exchange.getResponse().writeWith(Mono.just(exchange.getResponse().bufferFactory().wrap(bytes)));
	}

	@Override
	public int getOrder() {
		return Ordered.LOWEST_PRECEDENCE;
	}

}
