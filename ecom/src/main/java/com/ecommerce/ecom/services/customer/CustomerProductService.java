package com.ecommerce.ecom.services.customer;

import com.ecommerce.ecom.dto.ProductDto;

import java.util.List;

public interface CustomerProductService {
    List<ProductDto> getAllProducts();
    List<ProductDto> getAllProductByName(String name);
}
