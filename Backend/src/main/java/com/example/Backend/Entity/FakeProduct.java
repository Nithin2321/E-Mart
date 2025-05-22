package com.example.Backend.Entity;

public class FakeProduct {

    private String title;
    private String description;
    private double price;
    private String category;
    private String image;
    
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public FakeProduct(String title, String description, double price,String category, String image) {
		super();
		this.title = title;
		this.description = description;
		this.price = price;
		this.image = image;
		this.category=category;
	}
	public FakeProduct() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
    
}
