package com.online.shop.ecommerceshop.controller;

import com.online.shop.ecommerceshop.domain.HttpResponse;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;

import static com.online.shop.ecommerceshop.exception.ExceptionHandling.NO_MAPPING;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.ResponseEntity.status;

public class CustomErrorController implements ErrorController {
    public static final String ERROR_PATH = "/error";

    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }

    @RequestMapping(ERROR_PATH)
    public ResponseEntity<HttpResponse> notFound404() {
        return status(NOT_FOUND).body(new HttpResponse(NOT_FOUND.value(), NOT_FOUND,
                NOT_FOUND.getReasonPhrase().toUpperCase(), NO_MAPPING));
    }
}
