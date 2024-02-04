package com.springjwt.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springjwt.entities.Order;
import com.springjwt.services.other.OrderService;

@RestController
@RequestMapping("/api/order")
public class OrderController {

	@Autowired
	private OrderService orderService;

	@PostMapping("/placeorder")
	public ResponseEntity<?> placeOrder(@RequestParam Long userId) {
		String response = null;
		try {
			response = orderService.placeOrder(userId);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error Placing order");
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/getAllOrders")
	public ResponseEntity<?> getAllOrders() {
		try {
			List<Order> orderItems = orderService.getAllOrders();
			return new ResponseEntity<>(orderItems, HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error Retriving AllOrders");
		}
	}

	@GetMapping("/getOrdersByCustomerId")
	public ResponseEntity<?> getOrdersByCustomerId(@RequestParam Long customerId) {
		try {
			List<Order> orderItems = orderService.getAllOrdersByCustomerId(customerId);
			return new ResponseEntity<>(orderItems, HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error Retriving Orders for Customer Id : " + customerId);
		}
	}

	@PostMapping("/confirm-order")
	public ResponseEntity<?> confirmOrder(@RequestParam Long orderId) {
		try {
			String Response = orderService.confirmOrder(orderId);
			return new ResponseEntity<>(Response, HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error Confirming the orderId : " + orderId);
		}

	}

}
