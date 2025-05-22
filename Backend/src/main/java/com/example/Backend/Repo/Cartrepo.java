package com.example.Backend.Repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Backend.Entity.CartEntity;
import com.example.Backend.Entity.ProductEntity;
import com.example.Backend.Entity.UserReg;

public interface Cartrepo extends JpaRepository<CartEntity, Long> {
	List<CartEntity> findByUser(UserReg user);
	Optional<CartEntity> findByUserAndProduct(UserReg user,ProductEntity product);
	
	void deleteByUser(UserReg user);
	

}
