package com.example.Backend.Controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.Backend.Entity.UserReg;
import com.example.Backend.Services.UserService;

@CrossOrigin(origins="http://localhost:4200")
@RestController
public class UserController {

	
	@Autowired
	private UserService service;
	
	@PostMapping("/RegisterUser")
	public UserReg Register(@RequestBody UserReg user) {
	
		return service.registeruser(user);
	}
	
	@PutMapping("/UpdateUser/{id}")
	public UserReg Update(@PathVariable String id,@RequestBody UserReg update) {
		return service.updateuser(id, update);
	}
	
	@GetMapping("/Getuser/{id}")
	public Optional<UserReg> getuser(@PathVariable String id) {
		return service.getuser(id);
	}
	
	@GetMapping("/GetallUsers")
	public List<UserReg> getallusers() {
		return service.getallusers();
	}
	
	@DeleteMapping("/RemoveUser/{id}")
	public void deleteUser(@PathVariable String id) {
	     service.deleteuser(id);
	}

}
