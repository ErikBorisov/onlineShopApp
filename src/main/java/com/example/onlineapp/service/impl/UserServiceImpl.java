package com.example.onlineapp.service.impl;

import com.example.onlineapp.models.User;
import com.example.onlineapp.repositories.UserRepository;
import com.example.onlineapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    @Override
    public String save(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            return "Email already exist!";
        } else {

            user.setPassword(passwordEncoder.encode(user.getPassword()));
            String activationToken = UUID.randomUUID().toString();
            user.setActivationToken(activationToken);
            userRepository.save(user);
//            String mailBody = "To verify your registration please click on link or ignore it " +
//                    "http://localhost:8090/user/activate?token=" + activationToken + "&email=" + user.getEmail();
//            emailSender.sendSimpleMessage(user.getEmail(), "Account activation", mailBody);
            return "Please check your email";
        }
    }

    @Override
    public String activate(String token) {
        Optional<User> byActivationToken = userRepository.findByActivationToken(token);
        if (byActivationToken.isPresent()) {
            User user = byActivationToken.get();
            user.setActivationToken(null);
            userRepository.save(user);
            return "You succesfully activated your account.";
        }
        return "Something went wrong";
    }

    @Override
    public String verify(ModelMap modelMap, String token, String email) {
        Optional<User> userOpt = userRepository.findByEmail(email);
        if (userOpt.isPresent()) {

            User user = userOpt.get();
            if (user.getActivationToken() == null) {
                modelMap.addAttribute("isfirst", "1");
            } else if (user.getActivationToken().equals(token)) {
                user.setActivationToken(null);
                user.setActivationDate(LocalDateTime.now());
                userRepository.save(user);
                modelMap.addAttribute("isfirst", 0);
            } else {
                modelMap.addAttribute("isfirst", 2);
            }
        } else {
            modelMap.addAttribute("isfirst", 2);
        }
        return "mail-verification";
    }

    @Override
    public void changeUserStatus(final boolean status,
                                 final Long userId) {
        userRepository.findById(userId)
                .ifPresent(user -> {
                    user.setActive(status);
                    userRepository.save(user);
                });
    }
}
