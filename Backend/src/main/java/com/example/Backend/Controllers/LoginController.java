package com.example.Backend.Controllers;


import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.Backend.Entity.LoginRequest;
import com.example.Backend.Services.LoginService;


@CrossOrigin(origins="http://localhost:4200")
@RestController
public class LoginController {

	@Autowired
	private LoginService loginserv;
	
	@PostMapping("/login")
   public ResponseEntity<?> logins(@RequestBody LoginRequest log){
		try {
            String token = loginserv.login(log);

            // Send the token as a JSON response
            return ResponseEntity.ok(Map.of("token", token));

        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
   }

}
