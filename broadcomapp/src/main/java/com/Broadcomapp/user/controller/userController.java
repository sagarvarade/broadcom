package com.Broadcomapp.user.controller;

import com.Broadcomapp.user.beans.User;
import com.Broadcomapp.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;



@RestController
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
}
