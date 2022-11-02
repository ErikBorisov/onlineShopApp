package com.example.onlineapp.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class MvcConfig implements WebMvcConfigurer {

    @Value("${product.image.path}")
    private String productImagesFolder;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
          .addResourceHandler("/user/image/**")
                .addResourceLocations("file:C:\\Users\\Admin\\Desktop\\product_image\\");

//        registry.addResourceLocations(userImagesFolder);
    }
}