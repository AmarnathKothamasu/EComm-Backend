package com.springjwt.dto;

public class OrderDTO {

    private Long userId;
    private Long productId;
    private String productName;
    private int quantity;
	public OrderDTO(Long userId, Long productId, String productName, int quantity) {
		super();
		this.userId = userId;
		this.productId = productId;
		this.productName = productName;
		this.quantity = quantity;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
    
}
