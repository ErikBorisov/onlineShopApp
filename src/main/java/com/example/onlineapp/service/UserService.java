package com.example.onlineapp.service;

import com.example.onlineapp.models.User;
import org.springframework.ui.ModelMap;

public interface UserService {

    String save(User user);

    String activate(String token);

    String verify(ModelMap modelMap, String token, String email);

    void changeUserStatus(boolean status, Long userId);
}
