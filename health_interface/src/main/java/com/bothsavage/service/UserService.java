package com.bothsavage.service;

import com.bothsavage.pojo.User;

/**
 * 用户操作接口
 */
public interface UserService {
    public User findByUsername(String username);
}
