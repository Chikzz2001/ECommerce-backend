package com.ecommerce.ecom.entity;

import com.ecommerce.ecom.dto.WishlistDto;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Data
public class Wishlist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "product_id",nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "user_id",nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    public WishlistDto getDto() {
        WishlistDto wishlistDto=new WishlistDto();

        wishlistDto.setId(id);
        wishlistDto.setPrice(product.getPrice());
        wishlistDto.setProductId(product.getId());
        wishlistDto.setProductDescription(product.getDescription());
        wishlistDto.setProductName(product.getName());
        wishlistDto.setUserId(user.getId());
        wishlistDto.setReturnedImg(product.getImg());

        return wishlistDto;
    }
}
