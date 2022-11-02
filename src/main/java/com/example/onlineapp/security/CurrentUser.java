package com.example.onlineapp.security;


import com.example.onlineapp.models.User;
import org.springframework.security.core.authority.AuthorityUtils;

public class CurrentUser extends org.springframework.security.core.userdetails.User {

    private User user;

    //    public CurrentUser(User user) {
//        super(user.getEmail(), user.getPassword(), user.getActivationToken() == null, true, true, true, AuthorityUtils.createAuthorityList("user"));
//        this.user = user;
//    }
    public CurrentUser(User user) {
        super(user.getEmail(), user.getPassword(), user.getActivationToken() == null, true,
                true, user.isActive(), AuthorityUtils.createAuthorityList(user.getRole().name()));
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}
