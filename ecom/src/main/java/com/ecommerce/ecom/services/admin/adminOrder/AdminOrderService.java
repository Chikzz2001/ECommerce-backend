package com.ecommerce.ecom.services.admin.adminOrder;

import com.ecommerce.ecom.dto.AnalyticsResponse;
import com.ecommerce.ecom.dto.OrderDto;

import java.util.List;

public interface AdminOrderService {
    List<OrderDto> getAllPlacedOrders();

    OrderDto changeOrderStatus(Long orderId, String status);

    AnalyticsResponse calculateAnalytics();
}
