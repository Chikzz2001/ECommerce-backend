package com.ecommerce.ecom.controller.customer;

import com.ecommerce.ecom.entity.Coupon;
import com.ecommerce.ecom.services.customer.coupon.CustomerCouponService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/customer")
public class CustomerCouponController {

    private final CustomerCouponService customerCouponService;
    @GetMapping("/coupons")
    public ResponseEntity<List<Coupon>> getAllCoupons() {
        return ResponseEntity.ok(customerCouponService.getAllCoupons());
    }
}
