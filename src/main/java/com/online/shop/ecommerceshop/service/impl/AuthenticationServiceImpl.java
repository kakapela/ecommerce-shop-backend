package com.online.shop.ecommerceshop.service.impl;

import com.online.shop.ecommerceshop.domain.RefreshToken;
import com.online.shop.ecommerceshop.domain.User;
import com.online.shop.ecommerceshop.domain.UserPrincipal;
import com.online.shop.ecommerceshop.dto.*;
import com.online.shop.ecommerceshop.exception.domain.InvalidRefreshTokenException;
import com.online.shop.ecommerceshop.exception.domain.UserNotFoundException;
import com.online.shop.ecommerceshop.repository.UserRepository;
import com.online.shop.ecommerceshop.security.jwt.JwtTokenProvider;
import com.online.shop.ecommerceshop.service.AuthenticationService;
import com.online.shop.ecommerceshop.service.RefreshTokenService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;

import static com.online.shop.ecommerceshop.enumeration.Role.ROLE_USER;

@Service
@AllArgsConstructor
@Slf4j
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final RefreshTokenService refreshTokenService;

    @Override
    @Transactional
    public void signup(RegisterRequest registerRequest) {
        User user = new User();
        user.setUserId(generateUserId());
        user.setUsername(registerRequest.getUsername());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setFirstName(registerRequest.getFirstName());
        user.setLastName(registerRequest.getLastName());
        user.setRegistrationDate(new Date());
        user.setActive(true);
        user.setNotLocked(true);
        user.setRole(ROLE_USER);

        userRepository.save(user);
        log.info("New user was added!");


    }

    private String generateUserId() {
        return RandomStringUtils.randomNumeric(10);
    }

    @Override
    @Transactional
    public AuthenticationResponse login(LoginRequest loginRequest) {
       Authentication authentication =  authenticate(loginRequest.getUsername(), loginRequest.getPassword());

      UserPrincipal userPrincipal =  (UserPrincipal) authentication.getPrincipal();
      User user = userPrincipal.getUser();
      user.setLastLoginDate(new Date());
      userRepository.save(user);

        RefreshToken refreshToken = refreshTokenService.createRefreshToken(user.getId());
        String token = jwtTokenProvider.generateJwtToken(userPrincipal);

      return AuthenticationResponse.builder()
              .authenticationToken(token)
              .username(userPrincipal.getUsername())
              .refreshToken(refreshToken.getToken())
              .build();
    }

    @Override
    public TokenRefreshResponse refreshToken(TokenRefreshRequest request) throws InvalidRefreshTokenException {
        String requestRefreshToken = request.getRefreshToken();

        RefreshToken refreshToken = refreshTokenService
                .findByToken(requestRefreshToken)
                .orElseThrow(InvalidRefreshTokenException::new);

        boolean refreshTokenValid = refreshTokenService.isRefreshTokenValid(refreshToken);

        if (!refreshTokenValid)
            throw new InvalidRefreshTokenException();

        return new TokenRefreshResponse(
                jwtTokenProvider.generateJwtToken(new UserPrincipal(refreshToken.getUser())),
                refreshTokenService.createRefreshToken(refreshToken.getUser().getId()).getToken()
        );

    }
    @Override
    public void logout(String username) throws UserNotFoundException {
        User user = userRepository.findUserByUsername(username);
        //during logout we remove all refresh tokens
         refreshTokenService.deleteByUserId(user.getId());
    }

    private Authentication authenticate(String username, String password) {
        return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
    }
}
