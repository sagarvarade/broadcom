package com.apigateway.config;

import Bean.TokenDetails;
import com.apigateway.clients.CheckToken;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.util.Token;
import lombok.extern.slf4j.Slf4j;
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
import java.util.*;

@Component
@Slf4j
public class AuthenticationFilter implements GlobalFilter, Ordered {

	@Value("${broadcom_communication_token}")
	String broadcom_communication_token;

	@Autowired
	CheckToken checkTokenWithAuth;

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		try {
			log.info("Path ,{} ", exchange.getRequest().getPath());

			List<String> tokenForMicroCommunicationList = exchange.getRequest().getHeaders().get("broadcom_communication_token");

			if (tokenForMicroCommunicationList==null) {
				throw new RuntimeException("You are not allow to communicate to this services {}, Token missing for communication ");
			}

			String tokenForMicroCommunication = tokenForMicroCommunicationList.get(0);
			log.info("Token From Call ,{} ", tokenForMicroCommunication);
			log.info("Token Set For All microservices Call ,{} ", this.broadcom_communication_token);

			if (!tokenForMicroCommunication.equals(this.broadcom_communication_token)) {
				throw new RuntimeException("You are not allow to communicate to this services {} " + tokenForMicroCommunication);
			}

			if (exchange.getRequest().getPath().toString().indexOf("auth/authenticate") > 0) {
				log.info("Skipping this URL for authorization : ,{} ", exchange.getRequest().getPath());

				exchange.getRequest().mutate()
						.header("broadcom_communication_token", this.broadcom_communication_token)
						.build();

				return chain.filter(exchange);
			}
			String authHeader = Objects.requireNonNull(exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION)).get(0);
			String[] parts = authHeader.split(" ");
			log.info("Parts  : {}", Arrays.toString(parts));
			log.info("Bearer : {} ", parts[0]);
			log.info("Token  : {} ", parts[1]);

			if (parts.length != 2 || !"Bearer".equals(parts[0])) {
				throw new RuntimeException("Incorrect authorization structure");
			}

			int response = checkTokenWithAuth.checkToken(parts[1]).getStatusCodeValue();
			log.info("Response from Auth : {} ", response);
			if (response != 200) {
				throw new RuntimeException("Token is not valid " + response);
			}
			TokenDetails tokenDetails;
			try {
				tokenDetails = Token.getDecompressToken(parts[1]);
			} catch (JsonProcessingException e) {
				throw new RuntimeException(e);
			}
			System.out.println(" Mutating request:  with data " + tokenDetails);
			exchange.getRequest().mutate()
					.header("user_id", tokenDetails.getUserId())
					.header("exp", tokenDetails.getExpiry())
					.header("iat", tokenDetails.getIat())
					.header("roles", tokenDetails.getRoles())
					.header("broadcom_communication_token", this.broadcom_communication_token)
					.build();
			log.info("Userid: {}", tokenDetails.getUserId());
			log.info("exp   : {}", tokenDetails.getExpiry());
			log.info("iat   : {}", tokenDetails.getIat());
			log.info("roles : {}", tokenDetails.getRoles());

			return chain.filter(exchange);
		}
		catch(Exception e){
			log.error("Exception occurred at Authentication filter ", e);
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
