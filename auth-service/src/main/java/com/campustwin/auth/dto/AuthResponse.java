package com.campustwin.auth.dto;

import com.campustwin.auth.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse {

    private String token;
    private String refreshToken;

    private Long id;
    private String name;
    private String email;
    private String universityId;
    private Role role;
    private String department;
    private String block;
}