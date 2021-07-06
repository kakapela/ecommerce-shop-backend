package com.online.shop.ecommerceshop.service;

import com.online.shop.ecommerceshop.domain.RefreshToken;
import com.online.shop.ecommerceshop.domain.User;
import com.online.shop.ecommerceshop.exception.domain.InvalidRefreshTokenException;
import com.online.shop.ecommerceshop.exception.domain.UserNotFoundException;
import com.online.shop.ecommerceshop.repository.RefreshTokenRepository;
import com.online.shop.ecommerceshop.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import static com.online.shop.ecommerceshop.constant.SecurityConstant.REFRESH_TOKEN_EXPIRATION_TIME;
import static java.lang.String.valueOf;

@Service
@AllArgsConstructor
public class RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public Optional<RefreshToken> findByToken(String token) {
        return refreshTokenRepository.findByToken(token);
    }

    @Transactional
    public RefreshToken createRefreshToken(Long userId) {
        RefreshToken refreshToken = new RefreshToken();

        refreshToken.setUser(userRepository.findById(userId).get());
        refreshToken.setExpiryDate(Instant.now().plusMillis(REFRESH_TOKEN_EXPIRATION_TIME));
        refreshToken.setToken(UUID.randomUUID().toString());
        refreshToken.setValid(true);

        refreshToken = refreshTokenRepository.save(refreshToken);
        return refreshToken;
    }

    @Transactional
    public boolean isRefreshTokenValid(RefreshToken refreshToken){

        Boolean isValid = refreshToken.getValid();

        if (!isValid) {
            //if refresh token is not valid it means that he is used second time, hence it could be possible stolen, that is why we remove all user refresh tokens from db
                refreshTokenRepository.deleteByUser(refreshToken.getUser());
                return false;
        }

        if (refreshTokenExpired(refreshToken))
            return false;

        //if it's valid and not expired than set to valid for further use. Update refresh token
        refreshToken.setValid(false);
        refreshTokenRepository.save(refreshToken);
        return true;
    }

    @Transactional
    public void deleteByUserId(Long userId) throws UserNotFoundException {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(valueOf(userId)));
         refreshTokenRepository.deleteByUser(user);
    }

    private boolean refreshTokenExpired(RefreshToken refreshToken) {
        if (refreshToken.getExpiryDate().compareTo(Instant.now()) < 0)
        {
            //if refresh token expired delete it from memory
            refreshTokenRepository.delete(refreshToken);
            return true;
        }
        return false;
    }
}
