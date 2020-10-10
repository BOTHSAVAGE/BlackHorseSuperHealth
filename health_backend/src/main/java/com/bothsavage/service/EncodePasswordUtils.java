package com.bothsavage.service;

import com.bothsavage.utils.MyPasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EncodePasswordUtils {
    @Autowired
    MyPasswordEncoder encoder;

    public  String encodePassword(String password) {
        return encoder.encode(password);
    }
}