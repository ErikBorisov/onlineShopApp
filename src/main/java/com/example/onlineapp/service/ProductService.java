package com.example.onlineapp.service;

import com.example.onlineapp.models.Product;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductService {
    String save(Product product, MultipartFile productImage);

    List<Product> allProduct();

    void delete(Product product);
}
