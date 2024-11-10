package com.ecommerce.ecom.services.admin.category;

import com.ecommerce.ecom.dto.CategoryDto;
import com.ecommerce.ecom.entity.Category;
import com.ecommerce.ecom.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public Category createCategory(CategoryDto categoryDto) {
        Category category = new Category();
        category.setName(categoryDto.getName());
        category.setDescription(categoryDto.getDescription());

        categoryRepository.save(category);
        return category;
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }
}
