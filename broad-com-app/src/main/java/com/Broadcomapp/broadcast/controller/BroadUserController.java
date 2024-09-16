package com.Broadcomapp.broadcast.controller;


import com.Broadcomapp.broadcast.beans.BroadUser;
import com.Broadcomapp.broadcast.service.BroadUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;


@RestController
@RequestMapping("/broad-com-app/user")
public class BroadUserController {
	private final Logger log = LoggerFactory.getLogger(BroadUserController.class);
	@Autowired
	private BroadUserService userService;

	@GetMapping("/hello")
	public String getHello(){
		log.info("/broad-com-app/user/hello called ");
		return  "Hello World";
	}

	@PostMapping(path = "create",
    consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> createUser(@RequestBody BroadUser user,
											 @RequestHeader("user_id") String userID) {
		log.info("/broad-com-app/user/create called ");
		log.info("BroadUser {} , User Id : {} ",user,userID);
		try{
			LocalDateTime now=LocalDateTime.now();
			user.setCreatedBy(userID);
			user.setUpdatedBy(userID);
			user.setCreatedDate(now);
			user.setUpdatedDate(now);
			userService.createUser(user);
			return new ResponseEntity<>("User added Successfully.",HttpStatus.OK);
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return new ResponseEntity<>("Internal Server Error.",HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@PostMapping(path = "create-list",
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> createUser(@RequestBody List<BroadUser> users,
											 @RequestHeader("user_id") String userID) {
		log.info("/broad-com-app/user/create-list called ");
		log.info("BroadUser {} , User Id : {} ",users,userID);
		try{
			LocalDateTime now=LocalDateTime.now();
			for(BroadUser br:users){
				br.setCreatedBy(userID);
				br.setUpdatedBy(userID);
				br.setCreatedDate(now);
				br.setUpdatedDate(now);
			}
			userService.saveAll(users);
			return new ResponseEntity<>("User added Successfully.",HttpStatus.OK);
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return new ResponseEntity<>("Internal Server Error.",HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@GetMapping(path="get/{id}")
	public ResponseEntity<BroadUser> getUser(@PathVariable("id") Long id,
											 @RequestHeader("user_id") String userID){
		log.info("/broad-com-app/user/get/{id} called ");
		log.info("BroadUser Id {} , User Id : {} ",id,userID);
		if(userService.getUserByIdAndUpdatedBy(id,userID).isPresent()){
			return ResponseEntity.ok(userService.getUserByIdAndUpdatedBy(id,userID).get());
		}
		else{
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping(path="get-all")
	public ResponseEntity<List<BroadUser>> getAllUser(@RequestHeader("user_id") String userID){
		log.info("/broad-com-app/user/get-all called ");
		log.info("Broad  User Id : {} ",userID);
		List<BroadUser> brList= userService.getAllUsersForUpdatedBy(userID);
		return new ResponseEntity<>(brList,HttpStatus.OK);
	}

	@PutMapping(path="update")
	public  ResponseEntity<BroadUser> updateUser (@RequestBody BroadUser user,
												  @RequestHeader("user_id") String userID){
		log.info("/broad-com-app/user/update called ");
		log.info("Broad  User : {}, UserID : {} ",user,userID);
		LocalDateTime now=LocalDateTime.now();
		user.setUpdatedBy(userID);
		user.setUpdatedDate(now);
		return new ResponseEntity<>(userService.updateUser(user.getBroadUserId(),user),HttpStatus.OK);
	}

	@DeleteMapping(path="delete/{id}")
	public ResponseEntity<String> deleteUser (@PathVariable("id") Long id,
											  @RequestHeader("user_id") String userID){
		log.info("/broad-com-app/user/delete/{id} called ");
		log.info("BroadUser Id {} , User Id : {} ",id,userID);
		try{
			userService.deleteUserByIdAndUpdatedBy(id,userID);
			return new ResponseEntity<>("Deleted", HttpStatus.OK);
		}
		catch (Exception e){
			e.printStackTrace();
			log.info("Delete error : "+e.getMessage());
			log.info("Not Deleted userId {} ,  user{} ",id,userID);
			return new ResponseEntity<>("Not Deleted", HttpStatus.BAD_REQUEST);
		}
	}
}
