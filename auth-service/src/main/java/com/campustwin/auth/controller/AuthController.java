package com.campustwin.auth.controller;

import com.campustwin.auth.dto.AdminRegisterRequest;
import com.campustwin.auth.dto.AuthResponse;
import com.campustwin.auth.dto.LoginRequest;
import com.campustwin.auth.dto.RegisterRequest;
import com.campustwin.auth.entity.Role;
import com.campustwin.auth.entity.User;
import com.campustwin.auth.repository.UserRepository;
import com.campustwin.auth.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final UserRepository userRepository;

    // ── Public endpoints ──────────────────────────────────────────────────────

    // POST /api/auth/register — anyone, role always STUDENT
    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@Valid @RequestBody RegisterRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(authService.register(request));
    }

    // POST /api/auth/login
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }

    // POST /api/auth/refresh
    @PostMapping("/refresh")
    public ResponseEntity<AuthResponse> refresh(@RequestBody Map<String, String> request) {
        String refreshToken = request.get("refreshToken");
        if (refreshToken == null || refreshToken.isBlank()) {
            throw new RuntimeException("Refresh token is required");
        }
        return ResponseEntity.ok(authService.refreshAccessToken(refreshToken));
    }

    // ── Protected endpoints (any logged in user) ──────────────────────────────

    // POST /api/auth/logout
    @PostMapping("/logout")
    public ResponseEntity<Map<String, String>> logout(@AuthenticationPrincipal String email) {
        authService.logout(email);
        return ResponseEntity.ok(Map.of("message", "Logged out successfully"));
    }

    // GET /api/auth/me
    @GetMapping("/me")
    public ResponseEntity<AuthResponse> getMe(@AuthenticationPrincipal String email) {
        return ResponseEntity.ok(authService.getMe(email));
    }

    // ── Admin only endpoints ──────────────────────────────────────────────────

    // POST /api/auth/admin/register — sirf ADMIN, role choose kar sakta hai
    @PostMapping("/admin/register")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<AuthResponse> adminRegister(
            @Valid @RequestBody AdminRegisterRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(authService.adminRegister(request));
    }

    // GET /api/auth/admin/users — sabhi users ki list
    @GetMapping("/admin/users")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userRepository.findAll());
    }

    // PATCH /api/auth/admin/users/{id}/role — kisi ka role change karo
    @PatchMapping("/admin/users/{id}/role")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Map<String, String>> updateRole(
            @PathVariable Long id,
            @RequestBody Map<String, String> request) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Role newRole = Role.valueOf(request.get("role").toUpperCase());

        if (newRole == Role.ADMIN) {
            throw new RuntimeException("Cannot assign ADMIN role");
        }

        user.setRole(newRole);
        userRepository.save(user);

        return ResponseEntity.ok(Map.of("message",
                "Role updated to " + newRole + " for " + user.getName()));
    }
}