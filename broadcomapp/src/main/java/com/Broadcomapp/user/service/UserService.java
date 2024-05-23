package com.Broadcomapp.user.service;

import com.Broadcomapp.user.beans.User;
import com.Broadcomapp.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepo;

	public void createUser(User user) {
		userRepo.save(user);
	}
}
