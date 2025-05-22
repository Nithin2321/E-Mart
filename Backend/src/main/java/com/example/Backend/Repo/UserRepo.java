package com.example.Backend.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Backend.Entity.UserReg;

public interface UserRepo extends JpaRepository<UserReg, String> {

	UserReg findByEmail(String email);
}
