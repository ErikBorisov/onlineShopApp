package com.example.onlineapp.controler;

import com.example.onlineapp.models.User;
import com.example.onlineapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    @PostMapping("/register")
    public String register(@ModelAttribute User user,
                           RedirectAttributes redirectAttributes) {
        redirectAttributes.addAttribute("regInfo", userService.save(user));
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String pageLogin(ModelMap map,
                            @RequestParam(value = "error", required = false) String error) {
        map.addAttribute("login_user", new User());
        map.addAttribute("user", new User());
        return "index";
    }
}
