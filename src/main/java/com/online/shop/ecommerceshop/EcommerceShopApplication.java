package com.online.shop.ecommerceshop;

import com.online.shop.ecommerceshop.domain.Order;
import com.online.shop.ecommerceshop.domain.OrderProduct;
import com.online.shop.ecommerceshop.domain.Picture;
import com.online.shop.ecommerceshop.domain.Product;
import com.online.shop.ecommerceshop.repository.OrderProductRepository;
import com.online.shop.ecommerceshop.repository.OrderRepository;
import com.online.shop.ecommerceshop.repository.PictureRepository;
import com.online.shop.ecommerceshop.service.ProductService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class EcommerceShopApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcommerceShopApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(ProductService productService, PictureRepository pictureRepository, OrderRepository orderRepository,
							 OrderProductRepository orderProductRepository) {
		return args -> {
			Picture picture = new Picture(1L,
					"https://images.pexels.com/photos/914668/pexels-photo-914668.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500");
			List<Picture> pictures = new ArrayList<>();
			pictures.add(picture);
			pictureRepository.save(picture);
			Product product = new Product(1L, "Sukienka modowa", new BigDecimal("300.00"), "PLN", "Sukienka na lato 2021", "L", "niebieski", "DaFashion Original Brand Design",
					"damskie", "koszulki", LocalDateTime.now(), pictures);


			productService.save(product);
			picture.setProduct(product);
			pictureRepository.save(picture);

			for(int i=2; i<99; i++){
				Picture mockPicture = new Picture((long)i, "https://images.pexels.com/photos/914668/pexels-photo-914668.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500");

				List<Picture> picturesMock = new ArrayList<>();
				pictures.add(mockPicture);

				pictureRepository.save(mockPicture);



				Product mockProduct = new Product((long) i, "Sukienka modowa", new BigDecimal("300.00"), "PLN", "Sukienka na lato 2021", "L", "niebieski", "DaFashion Original Brand Design",
						"damskie", "koszulki", LocalDateTime.now(), picturesMock);

				productService.save(mockProduct);
				mockPicture.setProduct(mockProduct);
				pictureRepository.save(mockPicture);


			}

			Order order = new Order();
			order.setDateCreated(LocalDate.now());
			order.setStatus("PROCESSING");
			orderRepository.save(order);

			OrderProduct orderProduct = new OrderProduct(order, product, 2);
			orderProductRepository.save(orderProduct);




			//			productService.save(new Product(2L, "Game Console", new BigDecimal("200.00"), "http://placehold.it/200x100",23.5, "clothes", "green", "Panasonic", "telewizor służący do jedzenia"));
//			productService.save(new Product(3L, "Sofa", new BigDecimal("100.00"), "http://placehold.it/200x100",23.5, "clothes", "green", "Panasonic", "telewizor służący do jedzenia"));
//			productService.save(new Product(4L, "Icecream", new BigDecimal("200.00"), "http://placehold.it/200x100",23.5, "clothes", "green", "Panasonic", "telewizor służący do jedzenia"));
//			productService.save(new Product(5L, "Beer", new BigDecimal("3.00"), "http://placehold.it/200x100",23.5, "clothes", "green", "Panasonic", "telewizor służący do jedzenia"));
//			productService.save(new Product(6L, "Phone", new BigDecimal("500.00"), "http://placehold.it/200x100",23.5, "clothes", "green", "Panasonic", "telewizor służący do jedzenia"));
//			productService.save(new Product(7L, "Watch", new BigDecimal("7.00"), "http://placehold.it/200x100",23.5, "clothes", "green", "Panasonic", "telewizor służący do jedzenia"));

		};

	}
}
