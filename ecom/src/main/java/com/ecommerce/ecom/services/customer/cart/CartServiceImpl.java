package com.ecommerce.ecom.services.customer.cart;

import com.ecommerce.ecom.dto.AddProductInCartDto;
import com.ecommerce.ecom.entity.CartItems;
import com.ecommerce.ecom.entity.Order;
import com.ecommerce.ecom.entity.Product;
import com.ecommerce.ecom.entity.User;
import com.ecommerce.ecom.enums.OrderStatus;
import com.ecommerce.ecom.repository.CartItemsRepository;
import com.ecommerce.ecom.repository.OrderRepository;
import com.ecommerce.ecom.repository.ProductRepository;
import com.ecommerce.ecom.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartItemsRepository cartItemsRepository;

    private final UserRepository userRepository;

    private final OrderRepository orderRepository;

    private final ProductRepository productRepository;

    public ResponseEntity<?> addProductToCart(AddProductInCartDto addProductInCartDto) {
        Order activeOrder=orderRepository.findByUserIdAndOrderStatus(addProductInCartDto.getUserId(),
                OrderStatus.Pending);
        Optional<CartItems> optionalCartItems =
                cartItemsRepository.findByProductIdAndUserIdAndOrderId(addProductInCartDto.getProductId(),
                        addProductInCartDto.getUserId(),activeOrder.getId());

        if(optionalCartItems.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }
        else {
            Optional<Product> optionalProduct=productRepository.findById(addProductInCartDto.getProductId());
            Optional<User> optionalUser=userRepository.findById(addProductInCartDto.getUserId());
            if(optionalProduct.isPresent()&&optionalUser.isPresent()) {
                CartItems cart=new CartItems();
                cart.setProduct(optionalProduct.get());
                cart.setPrice(optionalProduct.get().getPrice());
                cart.setQuantity(1L);
                cart.setUser(optionalUser.get());
                cart.setOrder(activeOrder);

                CartItems updatedCart = cartItemsRepository.save(cart);

                activeOrder.setTotalAmount(activeOrder.getTotalAmount()+cart.getPrice());
                activeOrder.setAmount(activeOrder.getAmount()+cart.getPrice());
                activeOrder.getCartItems().add(cart);

                orderRepository.save(activeOrder);

                return ResponseEntity.status(HttpStatus.CREATED).body(cart);
            }
            else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User or product not found");
            }
        }
    }


}
