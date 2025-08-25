package com.demo.userauth.auth.dto;

import lombok.Data;

/**
 * Login Request
 */
@Data
public class LoginRequest {
    private String username;
    private String password;
}
