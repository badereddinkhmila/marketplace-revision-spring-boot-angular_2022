package com.ecommerce.marketplace.Service;

import com.ecommerce.marketplace.Entity.RefreshToken;

import java.util.Optional;

public interface IRefreshTokenService {
    RefreshToken verifyExpiration(RefreshToken token);

    int deleteByUserId(Long userId);

    RefreshToken createRefreshToken(Long userId);

    Optional<RefreshToken> findByToken(String token);
}
