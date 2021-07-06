package com.online.shop.ecommerceshop.exception;

import com.auth0.jwt.exceptions.TokenExpiredException;
import com.online.shop.ecommerceshop.domain.HttpResponse;
import com.online.shop.ecommerceshop.exception.domain.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;

import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@Slf4j
@RestControllerAdvice
public class ExceptionHandling {

    public static final String NO_USER_FOUND_BY_ID = "No user found by id: ";
    public static final String ID_TOO_LONG = "Given ID: %s is too long!";
    public static final String NO_MAPPING = "There is no mapping for this URL.";
    private static final String METHOD_IS_NOT_ALLOWED = "This request method is not allowed on this endpoint. Please send a '%s' request or other.";
    private static final String INTERNAL_SERVER_ERROR_MSG = "An error occurred while processing the request.";
    private static final String INCORRECT_CREDENTIALS = "Username / password incorrect. Please try again";
    private static final String NOT_ENOUGH_PERMISSION = "You do not have enough permission";
    private static final String ACCOUNT_LOCKED = "Your account has been locked. Please contact administration";
    public static final String PRODUCT_NOT_FOUND = "Product was not found";
    public static final String REFRESH_TOKEN_EXPIRED = "Refresh token was expired!";
    public static final String REFRESH_TOKEN_IS_INVALID = "Refresh token is invalid!";




    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<HttpResponse> userNotFoundException(UserNotFoundException exception) {
        log.error(exception.getMessage());
        return createHttpResponse(BAD_REQUEST, exception.getMessage());
    }

    @ExceptionHandler(InvalidRefreshTokenException.class)
    public ResponseEntity<HttpResponse> invalidRefreshTokenException(InvalidRefreshTokenException exception) {
        log.error(exception.getMessage());
        return createHttpResponse(UNAUTHORIZED, exception.getMessage());
    }

    @ExceptionHandler(RefreshTokenExpiredException.class)
    public ResponseEntity<HttpResponse> refreshTokenExpired(RefreshTokenExpiredException exception) {
        log.error(exception.getMessage());
        return createHttpResponse(UNAUTHORIZED, exception.getMessage());
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<HttpResponse> resourceNotFoundException(ResourceNotFoundException exception) {
        log.error(exception.getMessage());
        return createHttpResponse(BAD_REQUEST, exception.getMessage());
    }


    @ExceptionHandler(IdLengthException.class)
    public ResponseEntity<HttpResponse> idLengthException(IdLengthException exception) {
        log.error(exception.getMessage());
        return createHttpResponse(BAD_REQUEST, exception.getMessage());
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<HttpResponse> accessDeniedException() {
        return createHttpResponse(FORBIDDEN, NOT_ENOUGH_PERMISSION);
    }

    @ExceptionHandler(LockedException.class)
    public ResponseEntity<HttpResponse> lockedException() {
        return createHttpResponse(UNAUTHORIZED, ACCOUNT_LOCKED);
    }

    @ExceptionHandler(TokenExpiredException.class)
    public ResponseEntity<HttpResponse> tokenExpiredException(TokenExpiredException exception) {
        return createHttpResponse(UNAUTHORIZED, exception.getMessage());
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<HttpResponse> badCredentialsException(BadCredentialsException exception) {
        return createHttpResponse(BAD_REQUEST, INCORRECT_CREDENTIALS);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<HttpResponse> methodNotSupportedException(HttpRequestMethodNotSupportedException exception) {
        HttpMethod supportedMethod = Objects.requireNonNull(exception.getSupportedHttpMethods()).iterator().next();
        return createHttpResponse(METHOD_NOT_ALLOWED, String.format(METHOD_IS_NOT_ALLOWED, supportedMethod));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<HttpResponse> internalServerErrorException(Exception exception) {
        log.error(exception.toString());
        exception.getCause().printStackTrace();
        return createHttpResponse(INTERNAL_SERVER_ERROR, INTERNAL_SERVER_ERROR_MSG);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<HttpResponse> internalServerErrorException(RuntimeException exception) {
            log.error(exception.toString());
            exception.printStackTrace();
            return createHttpResponse(INTERNAL_SERVER_ERROR, INTERNAL_SERVER_ERROR_MSG);
    }

    private ResponseEntity<HttpResponse> createHttpResponse(HttpStatus httpStatus, String message) {
        return new ResponseEntity<>(new HttpResponse(httpStatus.value(), httpStatus,
                httpStatus.getReasonPhrase().toUpperCase(), message), httpStatus);
    }
}
