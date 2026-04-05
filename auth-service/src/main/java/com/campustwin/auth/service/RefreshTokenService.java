package com.campustwin.auth.service;

import com.campustwin.auth.entity.RefreshToken;
import com.campustwin.auth.entity.User;
import com.campustwin.auth.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {

    // 7 din milliseconds mein
    private static final long REFRESH_TOKEN_EXPIRY_MS = 7 * 24 * 60 * 60 * 1000L;

    private final RefreshTokenRepository refreshTokenRepository;

    // ── Naya refresh token banao ──────────────────────────────────────────────

    @Transactional
    public RefreshToken createRefreshToken(User user) {

        // Agar pehle se koi token hai toh delete karo — ek user, ek token
        refreshTokenRepository.deleteByUser(user);

        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setUser(user);
        refreshToken.setToken(UUID.randomUUID().toString()); // random unique string
        refreshToken.setExpiryDate(Instant.now().plusMillis(REFRESH_TOKEN_EXPIRY_MS));

        return refreshTokenRepository.save(refreshToken);
    }

    // ── Token dhundo aur verify karo ─────────────────────────────────────────

    public RefreshToken verifyRefreshToken(String token) {

        // Step 1: DB mein token hai?
        RefreshToken refreshToken = refreshTokenRepository.findByToken(token)
                .orElseThrow(() -> new RuntimeException("Refresh token not found"));

        // Step 2: Expire toh nahi hua?
        if (refreshToken.getExpiryDate().isBefore(Instant.now())) {
            refreshTokenRepository.delete(refreshToken); // expired token delete karo
            throw new RuntimeException("Refresh token expired. Please login again");
        }

        return refreshToken;
    }

    // ── Logout ke waqt token delete karo ─────────────────────────────────────

    @Transactional
    public void deleteByUser(User user) {
        refreshTokenRepository.deleteByUser(user);
    }
}