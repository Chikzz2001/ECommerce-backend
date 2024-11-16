package com.ecommerce.ecom.dto;

import com.ecommerce.ecom.enums.OrderStatus;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
public class OrderDto {
    private Long id;
    private String orderDescription;
    private String address;
    private Date date;
    private Long amount;
    private String payment;
    private OrderStatus orderStatus;
    private Long totalAmount;
    private Long discount;
    private UUID trackingId;
    private String userName;
    private List<CartItemsDto> cartItems;
    private String couponName;
}
