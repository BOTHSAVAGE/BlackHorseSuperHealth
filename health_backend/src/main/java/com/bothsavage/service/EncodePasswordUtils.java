package com.bothsavage.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class EncodePasswordUtils {
    @Autowired
    BCryptPasswordEncoder encoder;

    public  String encodePassword(String password) {
        return encoder.encode(password);
    }
}