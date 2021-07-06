package com.online.shop.ecommerceshop.service;

import com.online.shop.ecommerceshop.dto.*;
import com.online.shop.ecommerceshop.exception.domain.InvalidRefreshTokenException;
import com.online.shop.ecommerceshop.exception.domain.UserNotFoundException;

public interface AuthenticationService {
    void signup(RegisterRequest registerRequest);
    AuthenticationResponse login(LoginRequest loginRequest);
   TokenRefreshResponse refreshToken(TokenRefreshRequest request) throws InvalidRefreshTokenException;

    void logout(String username) throws UserNotFoundException;
}
