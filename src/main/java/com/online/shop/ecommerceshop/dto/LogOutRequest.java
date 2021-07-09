package com.online.shop.ecommerceshop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LogOutRequest {
    @NotBlank(message = "Refresh token cannot be empty!")
    private String refreshToken;
}


