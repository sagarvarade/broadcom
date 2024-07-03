package com.Broadcomapp.user.service;

import com.Broadcomapp.user.beans.BroadUser;
import com.Broadcomapp.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BroadUserService {

	@Autowired
	private UserRepository userRepo;
	public void createUser(BroadUser user) {
		userRepo.save(user);
	}
	public void saveAll(List<BroadUser> users) {
		userRepo.saveAll(users);
	}
	public BroadUser getUser(Long id){
		return  userRepo.getReferenceById(id);
	}
	public List<BroadUser> getAllUsers(){
		return userRepo.findAll();
	}
}
