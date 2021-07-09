package com.online.shop.ecommerceshop.service;

import com.online.shop.ecommerceshop.domain.User;
import com.online.shop.ecommerceshop.dto.*;
import com.online.shop.ecommerceshop.exception.domain.EmailExistException;
import com.online.shop.ecommerceshop.exception.domain.InvalidRefreshTokenException;
import com.online.shop.ecommerceshop.exception.domain.UserNotFoundException;
import com.online.shop.ecommerceshop.exception.domain.UsernameExistException;

public interface AuthenticationService {
    void signup(RegisterRequest registerRequest) throws UserNotFoundException, UsernameExistException, EmailExistException;

    AuthenticationResponse login(LoginRequest loginRequest);

    AuthenticationResponse refreshToken(TokenRefreshRequest request) throws InvalidRefreshTokenException;

    User findUserByEmail(String email);

    User findUserByUsername(String username);

    void logout(String refreshToken) throws UserNotFoundException, InvalidRefreshTokenException;
}
