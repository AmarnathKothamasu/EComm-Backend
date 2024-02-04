package com.springjwt.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springjwt.dto.ProductDTO;
import com.springjwt.dto.ProductIdDTO;
import com.springjwt.entities.Product;
import com.springjwt.services.other.ProductService;

@RestController
@RequestMapping("api/product/")
public class ProductController {

	@Autowired
	private ProductService productService ;
	
    @PostMapping("/add")
    public ResponseEntity<?> addproduct(@RequestBody ProductDTO productDto) {
    	ProductIdDTO createdProduct = productService.createProduct(productDto);
       if (createdProduct == null){
           return new ResponseEntity<>("Product not created!", HttpStatus.BAD_REQUEST);
       }
       return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
    }
    
    @GetMapping("/getAll")
    public ResponseEntity<?> getAllproducts(){
    	List<Product> products = productService.getAllProducts();
    	if (products == null) {
    		return new ResponseEntity<>("No products found or Badrequest!!",HttpStatus.NOT_FOUND);
    	}
    	return new ResponseEntity<>(products,HttpStatus.OK);
    }
    
    @GetMapping("/getById")
    public ResponseEntity<?> getProductById(@RequestParam Long productId) {
        if (productId == null) {
            return new ResponseEntity<>("Product ID cannot be null. Bad request.", HttpStatus.BAD_REQUEST);
        }
        Product product = productService.getProductById(productId);
        if (product == null) {
            return new ResponseEntity<>("Product not found for ID: " + productId, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(product, HttpStatus.OK);
    }
    
    @DeleteMapping("/deleteById")
    public ResponseEntity<?> deleteProductById(@RequestParam Long productId){
        if (productId == null) {
            return new ResponseEntity<>("Product ID cannot be null. Bad request.", HttpStatus.BAD_REQUEST);
        }
        String response = productService.deleteProductById(productId);
        if (response == null) {
            return new ResponseEntity<>("Product not found for ID: " + productId, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
