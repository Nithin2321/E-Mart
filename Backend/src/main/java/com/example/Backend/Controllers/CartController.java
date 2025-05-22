package com.example.Backend.Controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.Backend.Entity.CartEntity;
import com.example.Backend.Entity.ProductEntity;
import com.example.Backend.Entity.UserReg;
import com.example.Backend.Services.CartService;
import com.example.Backend.Services.ProductImportService;
import com.example.Backend.Services.UserService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class CartController {
	
	@Autowired
	private CartService cartservice;
	
	@Autowired
	private ProductImportService productService;
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/addToCart")
	public ResponseEntity<Map<String, String>> addToCart(
	    @RequestParam String userId,
	    @RequestParam String productId,
	    @RequestParam int quantity) {
	    
	    cartservice.addtocart(userId, productId, quantity);
	    return  ResponseEntity.ok(Map.of("message", "Product added to cart"));
	}

	@GetMapping("/Getcart/{UserId}")
	public List<CartEntity> getList(@PathVariable String UserId){
		
		return cartservice.getallcart(UserId);
	}
	
	@DeleteMapping("/remove")
	public ResponseEntity<Map<String, String>> removeFromCart(
	        @RequestParam("userId") String userId,
	        @RequestParam("productId") String productId) {

	    Optional<UserReg> optionalUser = userService.getuser(userId);
	    Optional<ProductEntity> optionalProduct = productService.getproductById(productId);

	    Map<String, String> response = new HashMap<>();

	    if (optionalUser.isPresent() && optionalProduct.isPresent()) {
	        UserReg user = optionalUser.get();
	        ProductEntity product = optionalProduct.get();

	        cartservice.removecartitem(user, product);

	        response.put("message", "Item removed from cart successfully.");
	        return ResponseEntity.ok(response);
	    } else {
	        response.put("error", "User or Product not found.");
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
	    }
	}

           
    }
	
	

