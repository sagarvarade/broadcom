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
	private final Logger log = LoggerFactory.getLogger(CallsWithOtherServices.class);

	@Autowired
	private Environment env;

	private String broadcom_communication_token;
	private String AUTH_URL;

	@PostConstruct
	private void postConstruct() {
		this.AUTH_URL = env.getProperty("authentication_service_url");
		this.broadcom_communication_token = env.getProperty("broadcom_communication_token");
	}

	public HttpResponse<String> checkToken(String token) {
		try {
			log.info("Checking token , {} ", token);
			HttpRequest request = HttpRequest.newBuilder().uri(new URI(AUTH_URL + "/auth/test-token"))
					.header("broadcom_communication_token", broadcom_communication_token)
					.header("Authorization", "Bearer " + token).GET().build();
			HttpClient httpClient = HttpClient.newHttpClient();
			System.out.println("Body Header : "+java.net.http.HttpResponse.BodyHandlers.ofString());
			HttpResponse<String> resp = httpClient.send(request, java.net.http.HttpResponse.BodyHandlers.ofString());
			log.info("Response : {} ", resp);
			return resp;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
