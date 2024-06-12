package com.Broadcomapp.user.controller;

import com.Broadcomapp.user.beans.User;
import com.Broadcomapp.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/broadcomapp")
public class userController {

	@Autowired
	private UserService userService;

	@PostMapping(path = "createuser",
    consumes = MediaType.APPLICATION_JSON_VALUE, 
    produces = MediaType.APPLICATION_JSON_VALUE)
	public String createUser(@RequestBody User user) {
		System.out.println(user);
		userService.createUser(user);
		return "User Created";
	}

	@PostMapping(path = "createuserlist",
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public String createUser(@RequestBody List<User> users) {
		userService.saveAll(users);
		return "User Created";
	}

	@GetMapping("/hello")
	public String getHello(){
		return  "Hello World";
	}
}
