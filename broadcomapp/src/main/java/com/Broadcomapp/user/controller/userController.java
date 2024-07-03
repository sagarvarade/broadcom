package com.Broadcomapp.user.controller;


import com.Broadcomapp.user.beans.BroadUser;
import com.Broadcomapp.user.service.BroadUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/broadcomapp")
public class userController {

	@Autowired
	private BroadUserService userService;

	@GetMapping("/hello")
	public String getHello(){
		return  "Hello World";
	}


	@PostMapping(path = "createuser",
    consumes = MediaType.APPLICATION_JSON_VALUE, 
    produces = MediaType.APPLICATION_JSON_VALUE)
	public String createUser(@RequestBody BroadUser user) {
		System.out.println(user);
		userService.createUser(user);
		return "User Created";
	}

	@PostMapping(path = "createuserlist",
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public String createUser(@RequestBody List<BroadUser> users) {
		userService.saveAll(users);
		return "User Created";
	}

	@GetMapping(path="get-user/{id}")
	public BroadUser getUser(@PathVariable Long id){
		return userService.getUser(id);
	}
	@GetMapping(path="get-all-user")
	public List<BroadUser> getAllUser(@PathVariable Long id){
		return userService.getAllUsers();
	}
}
