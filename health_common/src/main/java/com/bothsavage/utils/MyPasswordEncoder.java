package com.bothsavage.utils;

import org.springframework.security.crypto.password.PasswordEncoder;

public class MyPasswordEncoder implements PasswordEncoder {
    @Override
    public String encode(CharSequence rawPassword) {
        return MD5Utils.md5(rawPassword.toString());
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return MD5Utils.md5(rawPassword.toString()).equals(encodedPassword);
    }

    public static void main(String[] args) {
        MyPasswordEncoder encoder = new MyPasswordEncoder();
        System.out.println(encoder.encode("admin"));

    }

}
