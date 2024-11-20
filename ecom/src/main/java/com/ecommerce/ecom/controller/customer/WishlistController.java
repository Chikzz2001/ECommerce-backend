package com.ecommerce.ecom.controller.customer;

import com.ecommerce.ecom.dto.WishlistDto;
import com.ecommerce.ecom.services.customer.wishlist.WishlistService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/customer")
public class WishlistController {

    private final WishlistService wishlistService;

    @PostMapping("/wishlist")
    public ResponseEntity<?> addProductToWishList(@RequestBody WishlistDto wishlistDto) {
        WishlistDto wishlistDto1 = wishlistService.addProductToWishlist(wishlistDto);
        if (wishlistDto1 == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Something went wrong!");
        return ResponseEntity.status(HttpStatus.CREATED).body(wishlistDto1);
    }

    @GetMapping("/wishlist/{userId}")
    public ResponseEntity<List<WishlistDto>> getWishListByUserId(@PathVariable Long userId) {
        List<WishlistDto> wishlistDtoList = wishlistService.getWishlistByUserId(userId);
        return ResponseEntity.status(HttpStatus.OK).body(wishlistDtoList);
    }
}
