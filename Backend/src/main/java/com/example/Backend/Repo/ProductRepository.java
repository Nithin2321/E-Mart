package com.example.Backend.Repo;



import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Backend.Entity.ProductEntity;

public interface ProductRepository extends JpaRepository<ProductEntity, String> {

}
