package com.example.Backend.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class CartEntity {
	
    @Id 
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private UserReg user;

    @ManyToOne
    private ProductEntity product;

    private int quantity;

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

	public ProductEntity getProduct() {
		return product;
	}

	public void setProduct(ProductEntity product) {
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public CartEntity(Long id, UserReg user, ProductEntity product, int quantity) {
		super();
		this.id = id;
		this.user = user;
		this.product = product;
		this.quantity = quantity;
	}

	public CartEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "CartEntity [id=" + id + ", user=" + user + ", product=" + product + ", quantity=" + quantity + "]";
	}
    
    

}
