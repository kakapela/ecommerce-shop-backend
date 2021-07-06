package com.online.shop.ecommerceshop.controller;

import com.online.shop.ecommerceshop.dto.*;
import com.online.shop.ecommerceshop.exception.domain.InvalidRefreshTokenException;
import com.online.shop.ecommerceshop.exception.domain.UserNotFoundException;
import com.online.shop.ecommerceshop.service.AuthenticationService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;


    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody RegisterRequest registerRequest) {
        authenticationService.signup(registerRequest);
        return new ResponseEntity<>("User registration successful", OK);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody LoginRequest loginRequest) {
        return ok(authenticationService.login(loginRequest));
    }

    @PostMapping("/refreshtoken")
    public ResponseEntity<TokenRefreshResponse> refreshtoken(@Valid @RequestBody TokenRefreshRequest request) throws InvalidRefreshTokenException {
        return ok(authenticationService.refreshToken(request));
    }

    @PostMapping("/logout")
    @PreAuthorize("#logOutRequest.username == authentication.principal")
    public ResponseEntity<String> logoutUser(@Valid @RequestBody LogOutRequest logOutRequest) throws UserNotFoundException {
        authenticationService.logout(logOutRequest.getUsername());
        return  ResponseEntity.ok("Log out successful!");
    }



}
