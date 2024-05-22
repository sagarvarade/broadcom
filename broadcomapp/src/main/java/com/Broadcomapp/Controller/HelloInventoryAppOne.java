package com.Broadcomapp.Controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import com.google.common.net.HttpHeaders;

import jakarta.annotation.PostConstruct;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/helloworld")
public class HelloInventoryAppOne {

	WebClient webClient;

	@PostConstruct
	public void init() {
		webClient = WebClient.builder().baseUrl("https://httpbin.org/")
				.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).build();
	}

	public Mono<String> getString() {
		
		return (Mono<String>) webClient.post().uri("post").syncBody(new String()).retrieve().bodyToMono(String.class);

	}

	@GetMapping("/hello1")
	public String hello1() {
		return "Spring App 1 Controller";
	}
}
