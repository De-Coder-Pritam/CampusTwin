package com.campustwin.auth.dto;

import com.campustwin.auth.entity.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RegisterRequest {

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    private String phone;

    @NotBlank(message = "University ID is required")
    private String universityId;

    @NotBlank(message = "Password is required")
    private String password;


    private String department;

    private String block;
}