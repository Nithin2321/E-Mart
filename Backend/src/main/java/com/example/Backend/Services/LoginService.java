package com.example.Backend.Services;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.Backend.Entity.LoginRequest;
import com.example.Backend.Entity.UserReg;
import com.example.Backend.Repo.UserRepo;
import com.example.Backend.Security.JwtUtil;


@Service
public class LoginService {
	
	@Autowired
	private UserRepo repo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private JwtUtil jwtUtil;
	
    public String login(LoginRequest request) {
        UserReg user = repo.findByEmail(request.getEmail());

        if (user == null || !passwordEncoder.matches(request.getPassword(), user.getPassword())) {
             throw new RuntimeException("Invalid");
        }

        String token = jwtUtil.generateToken(user.getId(), user.getName(), user.getRole().name());

        return token;
    }
	
	}


