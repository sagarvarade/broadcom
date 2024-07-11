package com.Broadcomapp.BLogic.controller;


import com.Broadcomapp.BLogic.beans.BroadUser;
import com.Broadcomapp.BLogic.service.BroadUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/broadcomapp")
public class userController {

	@Autowired
	private BroadUserService userService;

	@GetMapping("/hello")
	public String getHello(){
		return  "Hello World";
	}


	@PostMapping(path = "create",
    consumes = MediaType.APPLICATION_JSON_VALUE, 
    produces = MediaType.APPLICATION_JSON_VALUE)
	public String createUser(@RequestBody BroadUser user) {
		System.out.println(user);
		userService.createUser(user);
		return "User Created";
	}

	@PostMapping(path = "create-list",
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public String createUser(@RequestBody List<BroadUser> users) {
		userService.saveAll(users);
		return "User Created";
	}

	@GetMapping(path="get/{id}")
	public BroadUser getUser(@PathVariable Long id){
		return userService.getUser(id);
	}
	@GetMapping(path="get-all")
	public List<BroadUser> getAllUser(@PathVariable Long id){
		return userService.getAllUsers();
	}

	@PutMapping(path="update")
	public  BroadUser updateUser (@RequestBody BroadUser user){
		return userService.updateUser(user.getBroadUserId(),user);
	}

	@GetMapping(path="delete/{id}")
	public ResponseEntity<String> deleteUser (@PathVariable("id") Long id){
		try{
			userService.deleteUserById(id);
			return new ResponseEntity("Deleted", HttpStatus.OK);
		}
		catch (Exception e){
			return new ResponseEntity("Not Deleted", HttpStatus.BAD_REQUEST);
		}
	}
}
