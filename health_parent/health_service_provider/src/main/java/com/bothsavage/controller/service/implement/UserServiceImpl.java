package com.bothsavage.controller.service.implement;

import com.alibaba.dubbo.config.annotation.Service;
import com.bothsavage.controller.service.UserService;
import com.bothsavage.dao.PermissionDao;
import com.bothsavage.dao.RoleDao;
import com.bothsavage.dao.UserDao;
import com.bothsavage.pojo.Permission;
import com.bothsavage.pojo.Role;
import com.bothsavage.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;


/**
 * 用户服务
 */
@Service(interfaceClass = UserService.class)
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    @Autowired
    RoleDao roleDao;

    @Autowired
    PermissionDao permissionDao;

    @Override
    public User findByUsername(String username) {
        //两种方法来查   1.直接mybatis书写  2.多次使用sql语句


        //todo 这样的话效率会降低 ，但是sql语句没那么麻烦


        //查询用户基本信息
        User user  = userDao.findByUsername(username);
        if(user == null){
            return  null;
        }

        //查询用户对应的角色
        Set<Role> roles = roleDao.findByUserId(user.getId());
        for (Role role:roles){
            Integer roleId = role.getId();
            //根据角色ID来查权限
            Set<Permission> permissionSet = permissionDao.findByRoleId(roleId);
            role.setPermissions(permissionSet);
        }
        user.setRoles(roles);//todo 我这个user放for循环的前面和放for循环的后面有什么区别
        //查询角色对应的权限
        return user;
    }
}
