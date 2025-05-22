package com.example.Backend.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Backend.Entity.OrderItemEntity;

public interface OrderItemrepo extends JpaRepository<OrderItemEntity, Long> {

}
