package com.ecommerce.ecom.services.customer.cart;

import com.ecommerce.ecom.dto.AddProductInCartDto;
import com.ecommerce.ecom.dto.CartItemsDto;
import com.ecommerce.ecom.dto.OrderDto;
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
import org.aspectj.weaver.ast.Or;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartItemsRepository cartItemsRepository;

    private final UserRepository userRepository;

    private final OrderRepository orderRepository;

    private final ProductRepository productRepository;

    public ResponseEntity<?> addProductToCart(AddProductInCartDto addProductInCartDto) {
        Order activeOrder = orderRepository.findByUserIdAndOrderStatus(addProductInCartDto.getUserId(),
                OrderStatus.Pending);
        Optional<User> user=userRepository.findById(addProductInCartDto.getUserId());

        if (activeOrder == null) {
            activeOrder = new Order();
            activeOrder.setUser(user.get()); // Assuming user is retrieved already
            activeOrder.setOrderStatus(OrderStatus.Pending);
            activeOrder = orderRepository.save(activeOrder);
        }

        System.out.print("\n\n\nproduct id: "+addProductInCartDto.getProductId()+" userid: "+addProductInCartDto.getUserId()+" orderId: "+activeOrder.getId()+"\n\n\n");
        Optional<CartItems> optionalCartItems =
                cartItemsRepository.findByProductIdAndUserIdAndOrderId(addProductInCartDto.getProductId(),
                        addProductInCartDto.getUserId(), activeOrder.getId());

        if (optionalCartItems.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        } else {

            Optional<Product> optionalProduct = productRepository.findById(addProductInCartDto.getProductId());
            Optional<User> optionalUser = userRepository.findById(addProductInCartDto.getUserId());
            if (optionalProduct.isPresent() && optionalUser.isPresent()) {
                CartItems cart = new CartItems();
                cart.setProduct(optionalProduct.get());
                cart.setPrice(optionalProduct.get().getPrice());
                cart.setQuantity(1L);
                cart.setUser(optionalUser.get());
                cart.setOrder(activeOrder);

                CartItems updatedCart = cartItemsRepository.save(cart);

                activeOrder.setTotalAmount(activeOrder.getTotalAmount() + cart.getPrice());
                activeOrder.setAmount(activeOrder.getAmount() + cart.getPrice());
                activeOrder.getCartItems().add(cart);

                orderRepository.save(activeOrder);

                return ResponseEntity.status(HttpStatus.CREATED).body(cart);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User or product not found");
            }
        }
    }

    public OrderDto getCartByUserId(Long userId) {
        Order activeOrder = orderRepository.findByUserIdAndOrderStatus(userId, OrderStatus.Pending);

        List<CartItemsDto> cartItemsDtoList = activeOrder.getCartItems().stream().map(CartItems::getCartsDto).toList();
        OrderDto orderDto = new OrderDto();
        orderDto.setAmount(activeOrder.getAmount());
        orderDto.setId(activeOrder.getId());
        orderDto.setOrderStatus(activeOrder.getOrderStatus());
        orderDto.setDiscount(activeOrder.getDiscount());
        orderDto.setTotalAmount(activeOrder.getTotalAmount());

        return orderDto;
    }


}
