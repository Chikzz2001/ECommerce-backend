package com.ecommerce.ecom.services.customer.review;

import com.ecommerce.ecom.dto.OrderedProductsResponseDto;

public interface ReviewService {
    OrderedProductsResponseDto getOrderedProductsDetailsByOrderId(Long orderId);
}
