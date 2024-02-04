package com.springjwt.services.other;

import java.util.List;

import org.springframework.stereotype.Service;

import com.springjwt.dto.ProductDTO;
import com.springjwt.dto.ProductIdDTO;
import com.springjwt.entities.Product;


public interface ProductService {
	
	ProductIdDTO createProduct(ProductDTO productDTO);
	
	List<Product> getAllProducts();

	Product getProductById(Long productId);
	
	String deleteProductById(Long productId);

}
