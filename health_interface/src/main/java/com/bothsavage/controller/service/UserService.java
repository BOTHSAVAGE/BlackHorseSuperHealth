package com.bothsavage.controller.service;

import com.bothsavage.pojo.User;

public interface UserService {
    public User findByUsername(String username);
}
