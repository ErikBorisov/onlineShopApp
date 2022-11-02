package com.example.onlineapp.repositories;

import com.example.onlineapp.models.Product;
import com.example.onlineapp.models.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {
}
