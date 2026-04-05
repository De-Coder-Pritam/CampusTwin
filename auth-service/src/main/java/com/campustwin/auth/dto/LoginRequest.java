package com.campustwin.auth.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequest {

    // user can login with either email or universityId
    @NotBlank(message = "Email or University ID is required")
    private String identifier;

    @NotBlank(message = "Password is required")
    private String password;
}