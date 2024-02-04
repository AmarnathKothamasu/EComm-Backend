package com.springjwt.services.other;

import java.util.List;

import com.springjwt.dto.TotalCostAndItem;
import com.springjwt.entities.Cart;

public interface CartService {

	List<Cart> getAllCartItemsById(long userId);
	
	Cart addItemToCart(long userId , long productId);

	void deleteItemFromCart(Long userId, Long productId);

	void ReduceItemCountByOne(Long userId, Long productId);

	Integer getTotalItems(Long userId);

	Integer getTotalCost(Long userId);

	TotalCostAndItem getTotalCostAndItems(Long userId);
}
