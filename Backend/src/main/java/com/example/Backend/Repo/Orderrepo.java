package com.example.Backend.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Backend.Entity.OrderEntity;
import com.example.Backend.Entity.UserReg;

public interface Orderrepo extends JpaRepository<OrderEntity,Long> {
	 List<OrderEntity> findByUser(UserReg user);
}
