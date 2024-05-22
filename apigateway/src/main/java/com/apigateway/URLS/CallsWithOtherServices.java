package com.apigateway.URLS;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class CallsWithOtherServices {
	private Logger logger = LoggerFactory.getLogger(CallsWithOtherServices.class);

	@Autowired
	private Environment env;

	private String AUTH_URL;

	@PostConstruct
	private void postConstruct() {
		this.AUTH_URL = env.getProperty("inventory.authentication_service_url");
	}

	public HttpResponse<String> checkToken(String token) {
		try {
			logger.info("Checking token , {} ", token);
			HttpRequest request = HttpRequest.newBuilder().uri(new URI(AUTH_URL + "/auth/testtoken"))
					.header("Authorization", "Bearer " + token).GET().build();
			HttpClient httpClient = HttpClient.newHttpClient();
			HttpResponse<String> resp = httpClient.send(request, java.net.http.HttpResponse.BodyHandlers.ofString());
			return resp;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
