package com.online.shop.ecommerceshop.controller;

import com.online.shop.ecommerceshop.domain.HttpResponse;
import com.online.shop.ecommerceshop.dto.*;
import com.online.shop.ecommerceshop.exception.domain.EmailExistException;
import com.online.shop.ecommerceshop.exception.domain.InvalidRefreshTokenException;
import com.online.shop.ecommerceshop.exception.domain.UserNotFoundException;
import com.online.shop.ecommerceshop.exception.domain.UsernameExistException;
import com.online.shop.ecommerceshop.service.AuthenticationService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static com.online.shop.ecommerceshop.constant.UserImplConstant.LOGOUT_SUCCESSFUL;
import static com.online.shop.ecommerceshop.constant.UserImplConstant.USER_REGISTRATION_SUCCESSFUL;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;


    @PostMapping("/signup")
    public ResponseEntity<HttpResponse> signup(@RequestBody RegisterRequest registerRequest) throws UserNotFoundException, UsernameExistException, EmailExistException {
        authenticationService.signup(registerRequest);
        return new ResponseEntity<>(new HttpResponse(OK.value(), OK, OK.getReasonPhrase(), USER_REGISTRATION_SUCCESSFUL), OK);
    }
    //TODO - HIBERNATE VALIDATION


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
    public ResponseEntity<HttpResponse> logoutUser(@Valid @RequestBody LogOutRequest logOutRequest) throws UserNotFoundException {
        authenticationService.logout(logOutRequest.getUsername());
        return new ResponseEntity<>(new HttpResponse(OK.value(), OK, OK.getReasonPhrase(), LOGOUT_SUCCESSFUL), OK);
    }



}
