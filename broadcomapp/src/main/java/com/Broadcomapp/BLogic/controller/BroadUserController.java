package com.Broadcomapp.BLogic.controller;


import com.Broadcomapp.BLogic.beans.BroadUser;
import com.Broadcomapp.BLogic.service.BroadUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;


@RestController
@RequestMapping("/broad-user")
public class BroadUserController {

	@Autowired
	private BroadUserService userService;

	@GetMapping("/hello")
	public String getHello(){
		return  "Hello World";
	}


	@PostMapping(path = "create",
    consumes = MediaType.APPLICATION_JSON_VALUE, 
    produces = MediaType.APPLICATION_JSON_VALUE)
	public String createUser(@RequestBody BroadUser user,@RequestHeader("user_id") String userID) {
		LocalDateTime now=LocalDateTime.now();
		user.setCreatedBy(userID);
		user.setUpdatedBy(userID);
		user.setCreatedDate(now);
		user.setUpdatedDate(now);
		userService.createUser(user);
		return "User Created";
	}

	@PostMapping(path = "create-list",
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public String createUser(@RequestBody List<BroadUser> users,@RequestHeader("user_id") String userID) {
		LocalDateTime now=LocalDateTime.now();
		for(BroadUser br:users){
			br.setCreatedBy(userID);
			br.setUpdatedBy(userID);
			br.setCreatedDate(now);
			br.setUpdatedDate(now);
		}
		userService.saveAll(users);
		return "User Created";
	}

	@GetMapping(path="get/{id}")
	public ResponseEntity<BroadUser> getUser(@PathVariable Long id,@RequestHeader("user_id") String userID){
		if(userService.getUserByIdAndUpdatedBy(id,userID).isPresent()){
			return ResponseEntity.ok(userService.getUserByIdAndUpdatedBy(id,userID).get());
		}
		else{
			return ResponseEntity.badRequest().build();
		}
	}

	@GetMapping(path="get-all")
	public List<BroadUser> getAllUser(@RequestHeader("user_id") String userID){
		return userService.getAllUsersForUpdatedBy(userID);
	}

	@PutMapping(path="update")
	public  BroadUser updateUser (@RequestBody BroadUser user,@RequestHeader("user_id") String userID){
		LocalDateTime now=LocalDateTime.now();
		user.setUpdatedBy(userID);
		user.setUpdatedDate(now);
		return userService.updateUser(user.getBroadUserId(),user);
	}

	@DeleteMapping(path="delete/{id}")
	public ResponseEntity<String> deleteUser (@PathVariable("id") Long id,@RequestHeader("user_id") String userID){
		try{
			userService.deleteUserByIdAndUpdatedBy(id,userID);
			return new ResponseEntity<String>("Deleted", HttpStatus.OK);
		}
		catch (Exception e){
			return new ResponseEntity<String>("Not Deleted", HttpStatus.BAD_REQUEST);
		}
	}
}
