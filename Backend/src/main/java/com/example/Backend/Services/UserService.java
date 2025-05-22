package com.example.Backend.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.Backend.Entity.UserReg;
import com.example.Backend.Repo.UserRepo;

@Service
public class UserService {
	
	@Autowired
	private UserRepo repo;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public UserReg registeruser(UserReg user) {
		 user.setPassword(passwordEncoder.encode(user.getPassword()));
		return repo.save(user);
	}
	
	public UserReg updateuser(String id,UserReg update) {
		UserReg user= repo.findById(id)
				.orElseThrow(() ->new RuntimeException("User not found"));
		user.setName(update.getName());
		user.setEmail(update.getEmail());
		user.setPhone(update.getPhone());
		user.setAddress(update.getAddress());
		user.setPincode(update.getPincode());
		return repo.save(user);
	}
	
	public Optional<UserReg> getuser(String id) {
		return repo.findById(id);
	}
	
	public List<UserReg> getallusers(){
		return repo.findAll();
	}
	
	public void deleteuser(String id) {
		repo.deleteById(id);
	}

	

}
