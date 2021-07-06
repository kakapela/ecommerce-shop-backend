package com.online.shop.ecommerceshop.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class TestController {

    @GetMapping("/hello")
    public String sayHello(){return "hello";}
}
