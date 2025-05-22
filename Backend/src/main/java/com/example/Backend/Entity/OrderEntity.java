package com.example.Backend.Entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "orders")
public class OrderEntity {

	    @Id @GeneratedValue
	    private Long id;

	    @ManyToOne
	    private UserReg user;

	    private LocalDateTime orderDate;

	    private double totalAmount;

	    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
	    @JsonManagedReference 
	    private List<OrderItemEntity> items = new ArrayList<>();

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public UserReg getUser() {
			return user;
		}

		public void setUser(UserReg user) {
			this.user = user;
		}

		public LocalDateTime getOrderDate() {
			return orderDate;
		}

		public void setOrderDate(LocalDateTime orderDate) {
			this.orderDate = orderDate;
		}

		public double getTotalAmount() {
			return totalAmount;
		}

		public void setTotalAmount(double totalAmount) {
			this.totalAmount = totalAmount;
		}

		public List<OrderItemEntity> getItems() {
			return items;
		}

		public void setItems(List<OrderItemEntity> items) {
			this.items = items;
		}

		public OrderEntity(Long id, UserReg user, LocalDateTime orderDate, double totalAmount,
				List<OrderItemEntity> items) {
			super();
			this.id = id;
			this.user = user;
			this.orderDate = orderDate;
			this.totalAmount = totalAmount;
			this.items = items;
		}

		public OrderEntity() {
			super();
			// TODO Auto-generated constructor stub
		}

		@Override
		public String toString() {
			return "OrderEntity [id=" + id + ", user=" + user + ", orderDate=" + orderDate + ", totalAmount="
					+ totalAmount + ", items=" + items + "]";
		}

	   
	    
	}


