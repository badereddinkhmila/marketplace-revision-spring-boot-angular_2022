package com.ecommerce.marketplace.Service.Implementation;

import com.ecommerce.marketplace.Entity.RefreshToken;
import com.ecommerce.marketplace.Exception.TokenRefreshException;
import com.ecommerce.marketplace.Repository.RefreshTokenRepository;
import com.ecommerce.marketplace.Repository.UserRepository;
import com.ecommerce.marketplace.Service.IRefreshTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
public class RefreshTokenService implements IRefreshTokenService {

    @Value("${app.jwtRefreshExpirationInMs}")
    private Long refreshTokenDurationMs;

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    @Autowired
    private UserRepository userRepository;

    /**
     * @param token
     * @return
     */
    @Override
    public RefreshToken verifyExpiration(RefreshToken token) {
        if (token.getExpiryDate().compareTo(Instant.now()) < 0) {
            refreshTokenRepository.delete(token);
            throw new TokenRefreshException(token.getToken(), "Refresh token was expired. Please make a new signin request");
        }
        return token;
    }

    /**
     * @param userId
     */
    @Override
    @Transactional
    public int deleteByUserId(Long userId) {
        return this.refreshTokenRepository.deleteByUser(this.userRepository.findById(userId).get());
    }

    /**
     * @param userId
     * @return
     */
    @Override
    public RefreshToken createRefreshToken(Long userId) {
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setUser(this.userRepository.findById(userId).get());
        refreshToken.setExpiryDate(Instant.now().plusMillis(refreshTokenDurationMs));
        refreshToken.setToken(UUID.randomUUID().toString());
        refreshToken = this.refreshTokenRepository.save(refreshToken);
        return refreshToken;
    }

    /**
     * @param token
     * @return
     */
    @Override
    public Optional<RefreshToken> findByToken(String token) {
        return this.refreshTokenRepository.findByToken(token);
    }
}
