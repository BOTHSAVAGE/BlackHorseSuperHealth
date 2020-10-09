package com.bothsavage.dao;

import com.bothsavage.pojo.Role;

import java.util.Set;

public interface RoleDao {
    public Set<Role> findByUserId(Integer userId);
}
