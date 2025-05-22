package com.example.Backend.Services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Backend.Entity.CartEntity;
import com.example.Backend.Entity.OrderEntity;
import com.example.Backend.Entity.OrderItemEntity;
import com.example.Backend.Entity.UserReg;
import com.example.Backend.Repo.Cartrepo;
import com.example.Backend.Repo.OrderItemrepo;
import com.example.Backend.Repo.Orderrepo;
import com.example.Backend.Repo.UserRepo;


@Service
public class OrderService {

	@Autowired
	private Cartrepo cartrepo;
	
	@Autowired
	private Orderrepo orderrepo;
	
	@Autowired
	private UserRepo userrepo;
	
	@Autowired
	private OrderItemrepo orderitemrepo;
	
	public String placeorder(UserReg user) {
		
		List<CartEntity> cartItem=cartrepo.findByUser(user);
		
        if (cartItem.isEmpty()) {
            return "Cart is empty. Cannot place order.";
        }
        OrderEntity order=new OrderEntity();
         order.setUser(user);
         order.setOrderDate(LocalDateTime.now());
       
       
         
         List<OrderItemEntity> items = cartItem.stream().map(cart -> {
             OrderItemEntity item = new OrderItemEntity();
             item.setOrder(order);
             item.setProduct(cart.getProduct());
             item.setQuantity(cart.getQuantity());
             item.setPrice(cart.getProduct().getPrice());
            
             return item;
         }).collect(Collectors.toList());
         
         double total = items.stream()
                 .mapToDouble(item -> item.getPrice() * item.getQuantity())
                 .sum();

         order.setTotalAmount(total);
         order.setItems(items);

          orderrepo.save(order);
          cartrepo.deleteAll(cartItem);  // Clear cart after placing order

          return "Order placed successfully!";
	}
	
    public List<OrderEntity> getOrdersByUserId(String userId) {
        UserReg user = userrepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return orderrepo.findByUser(user);
    }
	
	
}
