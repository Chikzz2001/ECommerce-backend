package com.ecommerce.ecom.services.customer;

import com.ecommerce.ecom.dto.ProductDetailDto;
import com.ecommerce.ecom.dto.ProductDto;
import com.ecommerce.ecom.entity.FAQ;
import com.ecommerce.ecom.entity.Product;
import com.ecommerce.ecom.entity.Review;
import com.ecommerce.ecom.repository.FAQRepository;
import com.ecommerce.ecom.repository.ProductRepository;
import com.ecommerce.ecom.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerProductServiceImpl implements CustomerProductService {
    private final ProductRepository productRepository;

    private final FAQRepository faqRepository;

    private final ReviewRepository reviewRepository;

    public List<ProductDto> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream().map(Product::getDto).collect(Collectors.toList());
    }

    public List<ProductDto> getAllProductByName(String name) {
        List<Product> products = productRepository.findAllByNameContaining(name);
        return products.stream().map(Product::getDto).collect(Collectors.toList());
    }

    public ProductDetailDto getProductDetailById(Long productId) {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if (optionalProduct.isPresent()) {
            List<FAQ> faqList = faqRepository.findAllByProductId(productId);
            List<Review> reviewList = reviewRepository.findAllByProductId(productId);

            ProductDetailDto productDetailDto = new ProductDetailDto();
            productDetailDto.setProductDto(optionalProduct.get().getDto());

            productDetailDto.setFaqDtoList(faqList.stream().map(FAQ::getFAQDto).collect(Collectors.toList()));
            productDetailDto.setReviewDtoList(reviewList.stream().map(Review::getDto).collect(Collectors.toList()));

            return productDetailDto;
        }
        return null;
    }
}
