package com.project.demo.logic.entity.user;

import lombok.Data;

@Data
public class LoginRequest {
    private String email;
    private String password;
}
