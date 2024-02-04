package com.springjwt.repositories;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.springjwt.entities.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart,Long>{
	List<Cart> findByUserUserId(Long userId);
	
	@Query("SELECT c FROM Cart c WHERE c.user.id = :userId AND c.product.id = :productId")
    Cart findByUserIdAndProductIdJPQL(@Param("userId") Long userId, @Param("productId") Long productId);
	
	@Modifying
    @Query("DELETE FROM Cart c WHERE c.user.id = :userId AND c.product.id = :productId")
    void deleteByUserIdAndProductId(@Param("userId") Long userId, @Param("productId") Long productId);
}
