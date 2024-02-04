package com.springjwt.dto;

public class ProductDTO {

	private String productName;
	private int productPrice;
	private int deliveryTimeSpan;
	private String productImageUrl;
	private String categoryName;
	private int quantity;
	
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
