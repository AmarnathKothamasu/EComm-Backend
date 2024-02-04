package com.springjwt.services.other;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springjwt.dto.TotalCostAndItem;
import com.springjwt.entities.Cart;
import com.springjwt.entities.Product;
import com.springjwt.entities.User;
import com.springjwt.repositories.CartRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class CartServiceImpl implements CartService {
	
	@Autowired
	private CartRepository cartRepository;

	@Override
	public List<Cart> getAllCartItemsById(long userId) {
		return cartRepository.findByUserUserId(userId);   
	}

	@Override
	public Cart addItemToCart(long userId, long productId) {
		Cart cartitem = cartRepository.findByUserIdAndProductIdJPQL(userId, productId);
		if(cartitem == null) {
			cartitem =  new Cart();
			cartitem.setUser(new User(userId));
			cartitem.setProduct(new Product(productId));
			cartitem.setQuantity(1);
		}
		else {
			cartitem.setQuantity(cartitem.getQuantity()+1);
		}
		return cartRepository.save(cartitem);
	}

	@Override
	public void deleteItemFromCart(Long userId, Long productId) {
		// Finding whether item is there are not in the cart with userId and ProductId
		Cart cartitem = cartRepository.findByUserIdAndProductIdJPQL(userId, productId);
		if(cartitem!=null) {
		cartRepository.deleteByUserIdAndProductId(userId,productId);
		}
	}

	@Override
	public void ReduceItemCountByOne(Long userId, Long productId) {
		// TODO Auto-generated method stub
		Cart cartitem = cartRepository.findByUserIdAndProductIdJPQL(userId, productId);
		// checking CartItem is null or not
		if(cartitem!=null) {
			// reducing the cartItem quantity by 1
			if(cartitem.getQuantity()>1) {
				cartitem.setQuantity(cartitem.getQuantity()-1);
				cartRepository.save(cartitem);
			}
			else {
				//cartRepository.deleteByUserIdAndProductId(userId,productId);
				cartRepository.delete(cartitem);
			}
		}
		throw new EntityNotFoundException();
	}

	@Override
	public Integer getTotalItems(Long userId) {
		// TODO Auto-generated method stub
		
		List<Cart> cartItems = cartRepository.findByUserUserId(userId);
		int totalItems = 0 ;
		for(Cart CartItem : cartItems) {
			totalItems += CartItem.getQuantity();
		}
		return totalItems;
	}

	@Override
	public Integer getTotalCost(Long userId) {
		// TODO Auto-generated method stub
		List<Cart> cartItems = cartRepository.findByUserUserId(userId);
		int totalCost = 0;
		
		for(Cart CartItem : cartItems) {
			Product productItem = CartItem.getProduct();
			totalCost += productItem.getProductPrice()*CartItem.getQuantity();
		}
		
		return totalCost;
	}

	@Override
	public TotalCostAndItem getTotalCostAndItems(Long userId) {
		// TODO Auto-generated method stub
		List<Cart> cartItems = cartRepository.findByUserUserId(userId);
		int totalItems = 0 ;
		int totalCost = 0;
		for(Cart CartItem : cartItems) {
			totalItems += CartItem.getQuantity();
			Product productItem = CartItem.getProduct();
			totalCost += productItem.getProductPrice()*CartItem.getQuantity();
		}
		TotalCostAndItem totalCostAndItem = new TotalCostAndItem();
		totalCostAndItem.setTotalItems(totalItems);
		totalCostAndItem.setTotalPrice(totalCost);
		return totalCostAndItem;
	}


}
