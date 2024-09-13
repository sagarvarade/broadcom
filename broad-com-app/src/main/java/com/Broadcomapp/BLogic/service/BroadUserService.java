package com.Broadcomapp.BLogic.service;

import com.Broadcomapp.BLogic.beans.BroadUser;
import com.Broadcomapp.BLogic.repository.BroadUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
	public Optional<BroadUser> getUserByIdAndUpdatedBy(Long id, String user){
		return  userRepo.findByBroadUserIdAndUpdatedBy(id,user);
	}
	public List<BroadUser> getAllUsersForUpdatedBy(String user){
		return userRepo.findAllByUpdatedBy(user);
	}

	public BroadUser updateUser(Long id, BroadUser updatedUser) {
		return userRepo.findByBroadUserIdAndUpdatedBy(id,updatedUser.getUpdatedBy()).map(user -> {
			user.setName(updatedUser.getName());
			user.setEmail(updatedUser.getEmail());
			user.setPhoneNumber(updatedUser.getPhoneNumber());
			user.setAddress(updatedUser.getAddress());
			user.setUpdatedBy(updatedUser.getUpdatedBy());
			user.setUpdatedDate(updatedUser.getUpdatedDate());
			return userRepo.save(user);
		}).orElseGet(() -> {
			updatedUser.setBroadUserId(id);
			return userRepo.save(updatedUser);
		});
	}

	public void deleteUserByIdAndUpdatedBy(Long id,String user) {
		 userRepo.deleteByBroadUserIdAndUpdatedBy(id,user);
	}
}
