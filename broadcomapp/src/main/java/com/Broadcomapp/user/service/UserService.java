package com.Broadcomapp.user.service;

import com.Broadcomapp.user.beans.User;
import com.Broadcomapp.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepo;

	public void createUser(User user) {
		userRepo.save(user);
	}

	public void saveAll(List<User> users) {
		userRepo.saveAll(users);
	}
	public User getUser(Long id){
		return  userRepo.getReferenceById(id);
	}

	public List<User> getAllUsers(){
		return userRepo.findAll();
	}
}
