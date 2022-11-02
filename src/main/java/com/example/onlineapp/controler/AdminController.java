package com.example.onlineapp.controler;

import com.example.onlineapp.models.Product;
import com.example.onlineapp.models.enums.Role;
import com.example.onlineapp.repositories.ProductRepository;
import com.example.onlineapp.repositories.UserRepository;
import com.example.onlineapp.security.CurrentUser;
import com.example.onlineapp.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    private final UserRepository userRepository;

    private final CategoryService categoryService;

    private final ProductRepository productRepository;

    @GetMapping("/home")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String adminHome(ModelMap modelMap,
                            @AuthenticationPrincipal UserDetails userDetails) {
        CurrentUser user = (CurrentUser) userDetails;
//        modelMap.addAttribute("allUser", userRepository.findUserByRoleIs(Role.USER));
//        modelMap.addAttribute("userArticle", user.getUser());
//        if (currentUser != null) {
            modelMap.addAttribute("currentUser", userRepository.findByEmail(user.getUser().getEmail()).get());
            modelMap.addAttribute("product", new Product());
            modelMap.addAttribute("categorys", categoryService.allCategory());
            modelMap.addAttribute("allProducts", productRepository.findAll());
//            modelMap.addAttribute("myArticles", articleService.articlesByAuthor(currentUser.getUser()));
//            modelMap.addAttribute("comment", new Comment());
//            modelMap.addAttribute("allArticles",articleService.allArticles());
            return "admin-page";
//        } else {
//            return "redirect:/login?errorMsg=Invalid credentials";
//        }
    }
}
