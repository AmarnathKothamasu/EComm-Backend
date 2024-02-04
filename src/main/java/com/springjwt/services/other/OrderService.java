package com.springjwt.services.other;

import java.util.List;

import com.springjwt.entities.Order;

public interface OrderService {

	String placeOrder(long userid);

	List<Order> getAllOrders();

	List<Order> getAllOrdersByCustomerId(Long customerId);

	String confirmOrder(Long orderId);
}
