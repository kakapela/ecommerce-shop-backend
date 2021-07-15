package com.online.shop.ecommerceshop.constant;

public class SecurityConstant {
    public static final long EXPIRATION_TIME = 360_0000; // 1 hour
    public static final long REFRESH_TOKEN_EXPIRATION_TIME = 86_400_000; // 1 day

    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String TOKEN_ISSUER="Dafashion Company";
    public static final String TOKEN_AUDIENCE="Ecommerce shop";
    public static final String AUTHORITIES = "authorities";
    public static final String TOKEN_CANNOT_BE_VERIFIED = "Token cannot be verified";
    public static final String OPTIONS_HTTP_METHOD = "OPTIONS";
    public static final String FORBIDDEN_MESSAGE = "Nie masz uprawnień do przeglądania tej strony";
    public static final String ACCESS_DENIED_MESSAGE = "Aby móc przeglądać tą stronę musisz się zalogować";
    public static final String[] PUBLIC_URLS = {  "/api/auth/login", "/api/auth/signup", "/api/auth/refreshtoken", "/api/auth/logout", "/api/products/**" };
}
