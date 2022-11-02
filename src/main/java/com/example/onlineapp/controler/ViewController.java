package com.example.onlineapp.controler;

import com.example.onlineapp.models.User;
import com.example.onlineapp.models.enums.Role;
import com.example.onlineapp.repositories.UserRepository;
import com.example.onlineapp.security.CurrentUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequiredArgsConstructor
public class ViewController {

    private final UserRepository userRepository;


    @GetMapping("/home")
    public String home(@AuthenticationPrincipal CurrentUser currentUser) {
        User user = currentUser.getUser();
        if (user.getRole() == Role.ADMIN) {
            return "redirect:/admin/home";
        } else if (user.getRole() == Role.MANAGER) {
            return "redirect:/manager/home";
        } else return "redirect:/user/home";
    }

    @GetMapping("/login")
    public String pageLogin(ModelMap map,
                            @RequestParam(value = "error", required = false) String error) {
        map.addAttribute("login_user", new User());
        map.addAttribute("user", new User());
        return "index";
    }

    @GetMapping("/verifyError")
    public String verifyError() {
        return "verifyError";
    }

    @GetMapping("/loginError")
    public String loginError() {
        return "loginError";
    }

    @PostMapping("/logout")
    public String logout() {
        return "index";
    }
}
