package com.Broadcomapp.URLS;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.util.Token;

@Component
public class CallsWithOtherServices {
	private Logger logger = LoggerFactory.getLogger(CallsWithOtherServices.class);

	//@Autowired
	//private Environment env;
	Token tk=new Token();
	private final String SPRING_APP_TWO ="";// env.getProperty("multiapp.springapp2");

	public HttpResponse<String> checkToken(String token) {
		try {
			logger.info("Checking token , {} ", token);
			HttpRequest request = HttpRequest.newBuilder().uri(new URI(SPRING_APP_TWO + "/auth/testtoken"))
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
