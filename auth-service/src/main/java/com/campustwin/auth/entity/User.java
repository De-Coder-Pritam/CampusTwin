package com.campustwin.auth.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "users")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(unique = true)
    private String phone;

    @Column(unique = true, nullable = false)
    private String universityId; // student ID / staff ID

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    private boolean active = true;

    private String department;

    private String block;
}