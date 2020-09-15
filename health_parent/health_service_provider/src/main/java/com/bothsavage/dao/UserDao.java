package com.bothsavage.dao;

import com.bothsavage.pojo.User;

public interface UserDao {
    User  findByUsername(String UserName);
}
