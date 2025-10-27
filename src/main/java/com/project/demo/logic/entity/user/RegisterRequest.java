package com.project.demo.logic.entity.user;

import lombok.Data;

@Data
public class RegisterRequest {
    private String name;
    private String lastname;
    private String email;
    private String password;
}
