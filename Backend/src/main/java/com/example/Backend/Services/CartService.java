package com.example.Backend.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Backend.Entity.CartEntity;
import com.example.Backend.Entity.ProductEntity;
import com.example.Backend.Entity.UserReg;
import com.example.Backend.Repo.Cartrepo;
import com.example.Backend.Repo.ProductRepository;
import com.example.Backend.Repo.UserRepo;

@Service
public class CartService {
	
	@Autowired
	private UserRepo userrepo;
	
	@Autowired
	private ProductRepository productrepo;
	
	@Autowired
	private Cartrepo cartrepo;
	
	
	public  String addtocart(String userId,String productId,int quantity) {
		
		UserReg user=userrepo.findById(userId).orElseThrow(()-> new RuntimeException("User not found"));
		
		ProductEntity product = productrepo.findById(productId).orElseThrow(()-> new RuntimeException("Product not found"));
		
		Optional<CartEntity> cartentity=cartrepo.findByUserAndProduct(user, product);
		
		if(cartentity.isPresent()) {
			CartEntity existing=cartentity.get();
			
			existing.setQuantity(existing.getQuantity()+quantity);
			cartrepo.save(existing);
		}
		else {
			CartEntity cart=new CartEntity();
			
			cart.setUser(user);
			cart.setProduct(product);
			cart.setQuantity(quantity);
			cartrepo.save(cart);
		}
		 return "Item added to cart";
		
	}
	
	public List<CartEntity> getallcart(String userId){
		UserReg user=userrepo.findById(userId).orElseThrow(()-> new RuntimeException("User not Found"));
        
		List<CartEntity> cart=cartrepo.findByUser(user);
		return cart;
	}
	
	public void removecartitem(UserReg user,ProductEntity product) {
		Optional<CartEntity> cart=cartrepo.findByUserAndProduct(user,product);
		cart.ifPresent(cartrepo::delete);
	}

}
