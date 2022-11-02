package com.example.onlineapp.configuration;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.File;

@Configuration
public class ApplicationConfig implements InitializingBean {

    @Value("${product.image.path}")
    private String productImagesFolder;


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }

//    @Bean
//    public ViewResolver resolver(){
//        ViewResolver viewResolver = new InternalResourceViewResolver();
//        viewResolver.suffix(".jsp)
//    }


//    @PostConstruct
//	public void run() throws Exception {
//		System.out.println("***** Creating app resources folders *****");
//		File file = new File(userImagesFolder);
//		if(file.mkdirs()){
//			System.out.println("Folder successfully created");
//		}
//	}

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("***** Creating app resources folders *****");
        File file = new File(productImagesFolder);
        if(file.mkdirs()){
            System.out.println("Folder successfully created");
        }
    }
}
