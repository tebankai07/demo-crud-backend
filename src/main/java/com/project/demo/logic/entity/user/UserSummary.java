package com.project.demo.logic.entity.user;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserSummary {
    private Long id;
    private String name;
    private String lastname;
    private String email;
    private String roleName;
}
