package com.springjwt.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "product")
public class Product {

	public Product(Long productId) {
		super();
		ProductId = productId;
	}
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long ProductId;

	public Product() {
		super();
	}
	private String productName;
	private int productPrice;
	private int deliveryTimeSpan;
	private String productImageUrl;
	private String categoryName;
	private int quantity;
	
	public Long getProductId() {
		return ProductId;
	}
	public void setProductId(Long ProductId) {
		this.ProductId = ProductId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public int getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(int productPrice) {
		this.productPrice = productPrice;
	}
	public int getDeliveryTimeSpan() {
		return deliveryTimeSpan;
	}
	public void setDeliveryTimeSpan(int deliveryTimeSpan) {
		this.deliveryTimeSpan = deliveryTimeSpan;
	}
	public String getProductImageUrl() {
		return productImageUrl;
	}
	public void setProductImageUrl(String productImageUrl) {
		this.productImageUrl = productImageUrl;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}
