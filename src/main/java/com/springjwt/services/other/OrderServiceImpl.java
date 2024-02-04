package com.springjwt.services.other;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.springjwt.entities.Cart;
import com.springjwt.entities.Order;
import com.springjwt.entities.Product;
import com.springjwt.repositories.CartRepository;
import com.springjwt.repositories.OrderRepository;
import com.springjwt.repositories.ProductRepository;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepository orderrespository;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private CartRepository cartRepository;

	@Override
	public String placeOrder(long userId) {
		List<Cart> cartItems = cartRepository.findByUserUserId(userId);

		for (Cart cartItem : cartItems) {
			// Move each item to the order table
			Order orderItem = new Order();
			orderItem.setUser(cartItem.getUser());
			orderItem.setProduct(cartItem.getProduct());
			orderItem.setQuantity(cartItem.getQuantity());
			orderItem.setOrderStatus("Ordered");
			orderrespository.save(orderItem);

			// updating the product Table quantity
			Optional<Product> optionalProduct = productRepository.findById(cartItem.getProduct().getProductId());
			if (optionalProduct.isPresent()) {
				Product product = optionalProduct.get();
				if (product.getQuantity() - cartItem.getQuantity() > 0) {
					product.setQuantity(product.getQuantity() - cartItem.getQuantity());
					productRepository.save(product);
				} else {
					// Insufficient quantity, return an error
					throw new RuntimeException(
							"Error: Insufficient quantity for product " + cartItem.getProduct().getProductName());
				}

			} else {
				throw new RuntimeException("Error: product " + cartItem.getProduct().getProductName() + "Not found");
			}

			// Deleting the cartitem
			cartRepository.delete(cartItem);

		}

		return "Orders placed successfully";
	}

	@Override
	public List<Order> getAllOrders() {
		List<Order> Orders = orderrespository.findAll();
		return Orders;
	}

	@Override
	public List<Order> getAllOrdersByCustomerId(Long customerId) {
		List<Order> Orders = orderrespository.findAllByuserId(customerId);
		return Orders;
	}

	@Override
	public String confirmOrder(Long orderId) {
		// TODO Auto-generated method stub

		Order order = orderrespository.getById(orderId);
		if (order.getOrderId() == orderId) {
			order.setOrderStatus("Confirmed");
			orderrespository.save(order);
		} else {
			throw new RuntimeException("OrderId not found");
		}
		return "Ordered Confirmed";
	}
}
