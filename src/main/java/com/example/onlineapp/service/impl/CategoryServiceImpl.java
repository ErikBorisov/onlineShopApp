package com.example.onlineapp.service.impl;

import com.example.onlineapp.models.ProductCategory;
import com.example.onlineapp.repositories.ProductCategoryRepository;
import com.example.onlineapp.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final ProductCategoryRepository categoryRepository;

    @Override
    public List<ProductCategory> allCategory() {
        return categoryRepository.findAll();
    }
}
