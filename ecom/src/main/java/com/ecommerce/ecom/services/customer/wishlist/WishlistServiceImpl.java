package com.ecommerce.ecom.services.customer.wishlist;

import com.ecommerce.ecom.dto.WishlistDto;
import com.ecommerce.ecom.entity.Product;
import com.ecommerce.ecom.entity.User;
import com.ecommerce.ecom.entity.Wishlist;
import com.ecommerce.ecom.repository.ProductRepository;
import com.ecommerce.ecom.repository.UserRepository;
import com.ecommerce.ecom.repository.WishlistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WishlistServiceImpl implements WishlistService {

    private final WishlistRepository wishlistRepository;

    private final UserRepository userRepository;

    private final ProductRepository productRepository;

    public WishlistDto addProductToWishlist(WishlistDto wishlistDto) {
        Optional<Product> optionalProduct=productRepository.findById(wishlistDto.getProductId());
        Optional<User> optionalUser=userRepository.findById(wishlistDto.getUserId());

        if(optionalProduct.isPresent() && optionalUser.isPresent()) {
            Wishlist wishlist=new Wishlist();
            wishlist.setProduct(optionalProduct.get());
            wishlist.setUser(optionalUser.get());

            return wishlistRepository.save(wishlist).getDto();
        }

        return null;
    }

    public List<WishlistDto> getWishlistByUserId(Long userId) {
        return wishlistRepository.findAllByUserId(userId).stream().map(Wishlist::getDto).collect(Collectors.toList());
    }
}
