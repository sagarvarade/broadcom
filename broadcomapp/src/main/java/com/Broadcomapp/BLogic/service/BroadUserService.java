package com.Broadcomapp.BLogic.service;

import com.Broadcomapp.BLogic.beans.BroadUser;
import com.Broadcomapp.BLogic.repository.BroadUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BroadUserService {

	@Autowired
	private BroadUserRepository userRepo;
	public void createUser(BroadUser user) {
		userRepo.save(user);
	}
	public void saveAll(List<BroadUser> users) {
		userRepo.saveAll(users);
	}
	public BroadUser getUser(Long id){
		return  userRepo.findById(id).get();
	}
	public List<BroadUser> getAllUsers(){
		return userRepo.findAll();
	}

	public BroadUser updateUser(Long id, BroadUser updatedUser) {
		return userRepo.findById(id).map(user -> {
			user.setName(updatedUser.getName());
			user.setEmail(updatedUser.getEmail());
			user.setPhoneNumber(updatedUser.getPhoneNumber());
			user.setAddress(updatedUser.getAddress());
			return userRepo.save(user);
		}).orElseGet(() -> {
			updatedUser.setBroadUserId(id);
			return userRepo.save(updatedUser);
		});
	}

	public void deleteUserById(Long id) {
		 userRepo.deleteById(id);
	}
}
