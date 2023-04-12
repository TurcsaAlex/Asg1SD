package com.example.concertapplication.config.security;

import org.springframework.security.core.userdetails.User;

import java.util.List;

public class AuthUser extends User {

    private final Integer id;

    public AuthUser(Integer id,
                    String username,
                    String encodedPassword,
                    String authority) {
        super(username, encodedPassword != null ? encodedPassword : "", List.of(new Authority(authority)));
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
}
