package com.springjwt.services.other;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springjwt.dto.ProductDTO;
import com.springjwt.dto.ProductIdDTO;
import com.springjwt.entities.Product;
import com.springjwt.repositories.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	@Override
	public ProductIdDTO createProduct(ProductDTO productDTO) {
		Product product = new Product();
		product.setProductName(productDTO.getProductName());
		product.setCategoryName(productDTO.getCategoryName());
		product.setDeliveryTimeSpan(productDTO.getDeliveryTimeSpan());
		product.setProductImageUrl(productDTO.getProductImageUrl());
		product.setProductPrice(productDTO.getProductPrice());
		product.setQuantity(productDTO.getProductPrice());
		Product createdProduct = productRepository.save(product);
		ProductIdDTO newProduct = new ProductIdDTO();
		newProduct.setProductId(createdProduct.getProductId());
		newProduct.setProductName(createdProduct.getProductName());
		newProduct.setCategoryName(createdProduct.getCategoryName());
		newProduct.setDeliveryTimeSpan(createdProduct.getDeliveryTimeSpan());
		newProduct.setProductImageUrl(createdProduct.getProductImageUrl());
		newProduct.setProductPrice(createdProduct.getProductPrice());
		newProduct.setQuantity(createdProduct.getQuantity());
		return newProduct;
	}

	@Override
	public List<Product> getAllProducts() {
		List<Product> Products = productRepository.findAll();
		return Products;
	}

	@Override
	public Product getProductById(Long productId) {
		Product product = productRepository.getById(productId);
		return product;
	}

	@Override
	public String deleteProductById(Long productId) {
		Product product = productRepository.getById(productId);
		if(product.getProductName()!=null) {
		productRepository.deleteById(productId);
		return "Product has been succesfully deleted";
		}
		return null;
		
	}

}
