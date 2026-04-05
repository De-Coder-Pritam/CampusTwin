package com.campustwin.auth.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.Instant;

@Entity
@Table(name = "refresh_tokens")
@Data
public class RefreshToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Har user ka ek hi active refresh token hoga
    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    // Random UUID string jo client ko bhejenge
    @Column(nullable = false, unique = true)
    private String token;

    // Yeh time ke baad token expire ho jayega
    @Column(nullable = false)
    private Instant expiryDate;
}