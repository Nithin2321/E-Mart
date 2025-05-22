package com.example.Backend.Entity;


import java.time.LocalDateTime;

import com.example.Backend.Enum.UserRole;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;

@Entity
public class UserReg {
	@Id
	@GeneratedValue(strategy=GenerationType.UUID)
	private String id;
	private String name;
	private String email;
	private String phone;
	
	@Enumerated(EnumType.STRING)
	private UserRole role;

	private String password;
	private String pincode;
	private String address;
	

	private LocalDateTime createdAt;
	
	@PrePersist
	public void prePersist() {
	    createdAt = LocalDateTime.now();
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPincode() {
		return pincode;
	}
	public void setPincode(String pincode) {
		this.pincode = pincode;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
    
	public UserReg(String id, String name, String email, String phone, UserRole role, String password, String pincode,
			String address, LocalDateTime createdAt) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.role = role;
		this.password = password;
		this.pincode = pincode;
		this.address = address;
		this.createdAt = createdAt;
	}
	public UserReg() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "UserReg [id=" + id + ", name=" + name + ", email=" + email + ", phone=" + phone + ", password="
				+ password + ", pincode=" + pincode + ", address=" + address + "]";
	}
	public UserRole getRole() {
		return role;
	}
	public void setRole(UserRole role) {
		this.role = role;
	}

	
	
	
}
