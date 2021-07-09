package com.online.shop.ecommerceshop.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class TokenRefreshRequest {

    @NotBlank(message = "Refresh Token cannot be empty!")
    private String refreshToken;
}
