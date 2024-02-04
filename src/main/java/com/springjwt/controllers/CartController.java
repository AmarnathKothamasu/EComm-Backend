package com.springjwt.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springjwt.dto.TotalCostAndItem;
import com.springjwt.entities.Cart;
import com.springjwt.services.other.CartService;

@RestController
@RequestMapping("/api/cart")
public class CartController {
	
	@Autowired
	private CartService cartService;
	
	@GetMapping("/getCartItemsByUserId")
	public ResponseEntity<?> getCartItemsByUserId(@RequestParam Long userId){
		List<Cart> cartItems = cartService.getAllCartItemsById(userId);
		
    	if (cartItems == null) {
    		return new ResponseEntity<>("No cartItems found or Badrequest!!",HttpStatus.NOT_FOUND);
    	}
    	return new ResponseEntity<>(cartItems,HttpStatus.OK);
	}
	
    @PostMapping("/addItemToCart")
    public ResponseEntity<?> addItemToCart(@RequestParam Long userId, @RequestParam Long productId) {
        try {
            cartService.addItemToCart(userId, productId);
            return ResponseEntity.ok("Item added to the cart successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error adding item to the cart");
        }
    }
    
    @DeleteMapping("/deleteItemFromCart")
    public ResponseEntity<?> deleteItemFromCart(@RequestParam Long userId, @RequestParam Long productId){
    	try {
    		cartService.deleteItemFromCart(userId,productId);
    		return ResponseEntity.ok("Item deleted from the cart successfully");
    	}catch(Exception e){
    		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    	}
    }
    
    @PatchMapping("/ReduceItemCountByOne")
    public ResponseEntity<?> ReduceItemCountByOne(@RequestParam Long userId, @RequestParam Long productId){
    	try {
    		cartService.ReduceItemCountByOne(userId,productId);
    		return ResponseEntity.ok("Item count has been Reduced from the cart successfully");
    	}catch(Exception e) {
    		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getCause());
    	}
    }
    @GetMapping("/total-items")
    public ResponseEntity<Integer> getTotalItems(@RequestParam Long userId) {
        return ResponseEntity.ok(cartService.getTotalItems(userId));
    }

    @GetMapping("/total-cost")
    public ResponseEntity<Integer> getTotalCost(@RequestParam Long userId) {
        return ResponseEntity.ok(cartService.getTotalCost(userId));
    }
    
    @GetMapping("/total-cost-items")
    public ResponseEntity<TotalCostAndItem> getTotalCostAndItems(@RequestParam Long userId) {
        return ResponseEntity.ok(cartService.getTotalCostAndItems(userId));
    }
    
}
