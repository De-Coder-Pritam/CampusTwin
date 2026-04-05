package com.campustwin.auth.service;

import com.campustwin.auth.dto.AdminRegisterRequest;
import com.campustwin.auth.dto.AuthResponse;
import com.campustwin.auth.dto.LoginRequest;
import com.campustwin.auth.dto.RegisterRequest;
import com.campustwin.auth.entity.RefreshToken;
import com.campustwin.auth.entity.Role;
import com.campustwin.auth.entity.User;
import com.campustwin.auth.repository.UserRepository;
import com.campustwin.auth.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final RefreshTokenService refreshTokenService;

    // ── Public Register — sirf STUDENT ───────────────────────────────────────

    public AuthResponse register(RegisterRequest request) {

        // @lpu.in email check
        validateLpuEmail(request.getEmail());

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already registered");
        }
        if (userRepository.existsByUniversityId(request.getUniversityId())) {
            throw new RuntimeException("University ID already registered");
        }

        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPhone(request.getPhone());
        user.setUniversityId(request.getUniversityId());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(Role.STUDENT); // always STUDENT
        user.setDepartment(request.getDepartment());
        user.setBlock(request.getBlock());

        User savedUser = userRepository.save(user);
        String accessToken = jwtService.generateToken(savedUser.getEmail(),savedUser.getRole());
        RefreshToken refreshToken = refreshTokenService.createRefreshToken(savedUser);

        return buildAuthResponse(savedUser, accessToken, refreshToken.getToken());
    }

    // ── Admin Register — role choose kar sakta hai, ADMIN nahi de sakta ──────

    public AuthResponse adminRegister(AdminRegisterRequest request) {

        // @lpu.in email check
        validateLpuEmail(request.getEmail());

        // ADMIN role dobara nahi de sakta — sirf ek admin hoga
        if (request.getRole() == Role.ADMIN) {
            throw new RuntimeException("Cannot create another ADMIN user");
        }

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already registered");
        }
        if (userRepository.existsByUniversityId(request.getUniversityId())) {
            throw new RuntimeException("University ID already registered");
        }

        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPhone(request.getPhone());
        user.setUniversityId(request.getUniversityId());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(request.getRole()); // admin jo role deta hai
        user.setDepartment(request.getDepartment());
        user.setBlock(request.getBlock());

        User savedUser = userRepository.save(user);
        String accessToken = jwtService.generateToken(savedUser.getEmail(),savedUser.getRole());
        RefreshToken refreshToken = refreshTokenService.createRefreshToken(savedUser);

        return buildAuthResponse(savedUser, accessToken, refreshToken.getToken());
    }

    // ── Login ─────────────────────────────────────────────────────────────────

    public AuthResponse login(LoginRequest request) {

        User user = userRepository.findByEmail(request.getIdentifier())
                .or(() -> userRepository.findByUniversityId(request.getIdentifier()))
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!user.isActive()) {
            throw new RuntimeException("Account is deactivated");
        }

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new BadCredentialsException("Invalid password");
        }

        String accessToken = jwtService.generateToken(user.getEmail(),user.getRole());
        RefreshToken refreshToken = refreshTokenService.createRefreshToken(user);

        return buildAuthResponse(user, accessToken, refreshToken.getToken());
    }

    // ── Refresh ───────────────────────────────────────────────────────────────

    public AuthResponse refreshAccessToken(String refreshToken) {
        RefreshToken verified = refreshTokenService.verifyRefreshToken(refreshToken);
        User user = verified.getUser();
        String newAccessToken = jwtService.generateToken(user.getEmail(),user.getRole());
        return buildAuthResponse(user, newAccessToken, verified.getToken());
    }

    // ── Logout ────────────────────────────────────────────────────────────────

    public void logout(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        refreshTokenService.deleteByUser(user);
    }

    // ── Get current user ──────────────────────────────────────────────────────

    public AuthResponse getMe(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return buildAuthResponse(user, null, null);
    }

    // ── Private helpers ───────────────────────────────────────────────────────

    private void validateLpuEmail(String email) {
        if (!email.endsWith("@lpu.in")) {
            throw new RuntimeException("Only @lpu.in email addresses are allowed");
        }
    }

    private AuthResponse buildAuthResponse(User user, String accessToken, String refreshToken) {
        return AuthResponse.builder()
                .token(accessToken)
                .refreshToken(refreshToken)
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .universityId(user.getUniversityId())
                .role(user.getRole())
                .department(user.getDepartment())
                .block(user.getBlock())
                .build();
    }
}