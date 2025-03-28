package com.ecommerce.ecom.repository;

import com.ecommerce.ecom.entity.CartItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartItemsRepository extends JpaRepository<CartItems, Long> {
    Optional<CartItems> findByProductIdAndUserIdAndOrderId(Long productId, Long userId, Long orderId);
}
