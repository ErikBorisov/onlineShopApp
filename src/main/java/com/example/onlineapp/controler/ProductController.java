package com.example.onlineapp.controler;


import com.example.onlineapp.models.Product;
import com.example.onlineapp.service.ProductService;
import com.nimbusds.jose.util.Resource;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.FileInputStream;
import java.io.IOException;

@Controller
@RequiredArgsConstructor
@RequestMapping("/product")
@Log4j2
public class ProductController {

    @Value("${product.image.path}")
    private String productImagesFolder;
    private ProductService productService;

//    @PostMapping
//    @PreAuthorize("hasAuthority('ADMIN')")
//    public String addProduct(@ModelAttribute Product product,
//                             @RequestPart(name = "productImage") MultipartFile productImage) {
//        product.setImage(String.valueOf(productImage));
//        Product savedProduct = productService.save(product, productImage);
//        log.info("Saving new Product {}", savedProduct);
//        return "redirect:/user/home";
//    }
    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public String register(@ModelAttribute Product product,
                           @RequestPart(name = "productImage") MultipartFile productImage,
                           RedirectAttributes redirectAttributes) {
        redirectAttributes.addAttribute("regInfo", productService.save(product, productImage));
        return "redirect:/user/home";
    }
}
