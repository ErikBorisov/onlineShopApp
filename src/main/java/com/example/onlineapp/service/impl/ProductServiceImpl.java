package com.example.onlineapp.service.impl;

import com.example.onlineapp.models.Product;
import com.example.onlineapp.repositories.ProductRepository;
import com.example.onlineapp.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Value("${product.image.path}")
    private String productImagesFolder;

    @SneakyThrows
    @Override
    public String save(Product product, MultipartFile productImage) {
        String productImageName = System.currentTimeMillis() + "_" + productImage.getOriginalFilename();
        productImage.transferTo(new File(productImagesFolder + productImageName));

        product.setImage(productImageName);

        productRepository.save(product);
        return "Success save product";
    }

    @Override
    public List<Product> allProduct() {
        return productRepository.findAll();
    }

    @Override
    public void delete(Product product) {

    }
}
