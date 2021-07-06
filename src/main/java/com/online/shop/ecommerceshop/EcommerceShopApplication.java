package com.online.shop.ecommerceshop;

import com.online.shop.ecommerceshop.domain.Product;
import com.online.shop.ecommerceshop.service.ProductService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;

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

	@Bean
	public CorsFilter corsFilter() {
		UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
		CorsConfiguration corsConfiguration = new CorsConfiguration();
		corsConfiguration.setAllowCredentials(true);
		corsConfiguration.setAllowedOrigins(Arrays.asList("http://localhost/**"));
		corsConfiguration.setAllowedHeaders(Arrays.asList("Origin", "Access-Control-Allow-Origin", "Content-Type",
				"Accept", "Jwt-Token", "Authorization", "Origin, Accept", "X-Requested-With",
				"Access-Control-Request-Method", "Access-Control-Request-Headers"));
		corsConfiguration.setExposedHeaders(Arrays.asList("Origin", "Content-Type", "Accept", "Jwt-Token", "Authorization",
				"Access-Control-Allow-Origin", "Access-Control-Allow-Origin", "Access-Control-Allow-Credentials"));
		corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
		urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
		return new CorsFilter(urlBasedCorsConfigurationSource);

//		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//		CorsConfiguration config = new CorsConfiguration();
//		//config.setAllowCredentials(true); // you USUALLY want this
//		config.addAllowedOrigin("*");
//		config.addAllowedHeader("*");
//		config.addAllowedMethod("OPTIONS");
//		config.addAllowedMethod("HEAD");
//		config.addAllowedMethod("GET");
//		config.addAllowedMethod("PUT");
//		config.addAllowedMethod("POST");
//		config.addAllowedMethod("DELETE");
//		config.addAllowedMethod("PATCH");
//		source.registerCorsConfiguration("/**", config);
//		return new CorsFilter(source);
	}


}
