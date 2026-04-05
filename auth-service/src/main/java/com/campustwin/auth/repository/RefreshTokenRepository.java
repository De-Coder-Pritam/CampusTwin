package com.campustwin.auth.repository;

import com.campustwin.auth.entity.RefreshToken;
import com.campustwin.auth.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

    java.util.Optional<RefreshToken> findByToken(String token);

    // @Modifying + @Query se delete pehle execute hoga, phir insert
    @Modifying
    @Query("DELETE FROM RefreshToken rt WHERE rt.user = :user")
    void deleteByUser(User user);
}