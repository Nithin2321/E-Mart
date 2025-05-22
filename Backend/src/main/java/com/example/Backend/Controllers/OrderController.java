package com.example.Backend.Controllers;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import com.example.Backend.Entity.OrderEntity;
import com.example.Backend.Entity.UserReg;

import com.example.Backend.Services.OrderService;
import com.example.Backend.Services.UserService;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userserv;
    @PostMapping("/placeOrder")
    public ResponseEntity<Map<String, String>> placeOrder(@RequestParam String userId) {
        UserReg user = userserv.getuser(userId).orElse(null);
        if (user == null) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("message", "User not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }

        String result = orderService.placeorder(user);

        Map<String, String> response = new HashMap<>();
        response.put("message", result);
        return ResponseEntity.ok(response);
    }


    @GetMapping("/getorders/{userId}")
    public List<OrderEntity> getOrders(@PathVariable String userId) {
        List<OrderEntity> orders = orderService.getOrdersByUserId(userId);
        if (orders == null || orders.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No orders found for user " + userId);
        }
        return orders;
    }
}

