package com.ecommerce.ecom.services.customer.review;

import com.ecommerce.ecom.dto.OrderedProductsResponseDto;
import com.ecommerce.ecom.dto.ProductDto;
import com.ecommerce.ecom.entity.CartItems;
import com.ecommerce.ecom.entity.Order;
import com.ecommerce.ecom.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final OrderRepository orderRepository;

    public OrderedProductsResponseDto getOrderedProductsDetailsByOrderId(Long orderId) {
        Optional<Order> optionalOrder=orderRepository.findById(orderId);
        OrderedProductsResponseDto orderedProductsResponseDto=new OrderedProductsResponseDto();

        if(optionalOrder.isPresent()) {
            orderedProductsResponseDto.setOrderAmount(optionalOrder.get().getAmount());

            List<ProductDto> productDtoList = new ArrayList<>();
            for(CartItems cartItems:optionalOrder.get().getCartItems()) {
                ProductDto productDto=new ProductDto();

                productDto.setId(cartItems.getProduct().getId());
                productDto.setName(cartItems.getProduct().getName());
                productDto.setPrice(cartItems.getPrice());
                productDto.setQuantity(cartItems.getQuantity());
                productDto.setByteImg(cartItems.getProduct().getImg());

                productDtoList.add(productDto);
            }
            orderedProductsResponseDto.setProductDtoList(productDtoList);
        }
        return orderedProductsResponseDto;
    }
}
