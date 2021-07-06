package com.online.shop.ecommerceshop;

import com.online.shop.ecommerceshop.domain.Product;
import com.online.shop.ecommerceshop.service.ProductService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;

@SpringBootApplication
public class EcommerceShopApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcommerceShopApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(ProductService productService) {
		return args -> {
			productService.save(new Product(1L, "TV Set", new BigDecimal("300.00"), "http://placehold.it/200x100", 23.5, "clothes", "green", "Panasonic", "telewizor służący do jedzenia"));
			productService.save(new Product(2L, "Game Console", new BigDecimal("200.00"), "http://placehold.it/200x100",23.5, "clothes", "green", "Panasonic", "telewizor służący do jedzenia"));
			productService.save(new Product(3L, "Sofa", new BigDecimal("100.00"), "http://placehold.it/200x100",23.5, "clothes", "green", "Panasonic", "telewizor służący do jedzenia"));
			productService.save(new Product(4L, "Icecream", new BigDecimal("200.00"), "http://placehold.it/200x100",23.5, "clothes", "green", "Panasonic", "telewizor służący do jedzenia"));
			productService.save(new Product(5L, "Beer", new BigDecimal("3.00"), "http://placehold.it/200x100",23.5, "clothes", "green", "Panasonic", "telewizor służący do jedzenia"));
			productService.save(new Product(6L, "Phone", new BigDecimal("500.00"), "http://placehold.it/200x100",23.5, "clothes", "green", "Panasonic", "telewizor służący do jedzenia"));
			productService.save(new Product(7L, "Watch", new BigDecimal("7.00"), "http://placehold.it/200x100",23.5, "clothes", "green", "Panasonic", "telewizor służący do jedzenia"));

		};

	}
}
