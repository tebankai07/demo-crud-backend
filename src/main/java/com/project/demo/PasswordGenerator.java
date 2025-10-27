package com.project.demo;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordGenerator {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encoded = encoder.encode("12345");
        System.out.println("Tu hash BCrypt es:");
        System.out.println(encoded);
    }
}
