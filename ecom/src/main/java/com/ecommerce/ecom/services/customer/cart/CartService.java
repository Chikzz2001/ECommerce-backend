package com.ecommerce.ecom.services.customer.cart;

import com.ecommerce.ecom.dto.AddProductInCartDto;
import com.ecommerce.ecom.dto.OrderDto;
import org.springframework.http.ResponseEntity;

public interface CartService {
    ResponseEntity<?> addProductToCart(AddProductInCartDto addProductInCartDto);

    OrderDto getCartByUserId(Long userId);
}
