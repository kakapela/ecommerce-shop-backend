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
					"women", "koszulki", LocalDateTime.now(),9, pictures);


			productService.save(product);
			picture.setProduct(product);
			pictureRepository.save(picture);

			for(int i=2; i<99; i++){
				int min = 800;
				int max = 1000;
				int random_int = (int)Math.floor(Math.random()*(max-min+1)+min);
				Picture mockPicture = new Picture((long)i, "https://source.unsplash.com/collection/"+ i );
				Picture mockPicture2 = new Picture((long)i+100, "https://source.unsplash.com/collection/"+ i );
				Picture mockPicture3 = new Picture((long)i+101, "https://source.unsplash.com/collection/"+ i );
				Picture mockPicture4 = new Picture((long)i+102, "https://source.unsplash.com/collection/"+ i );
				Picture mockPicture5 = new Picture((long)i+103, "https://source.unsplash.com/collection/"+ i );
				Picture mockPicture6 = new Picture((long)i+104, "https://source.unsplash.com/collection/"+ i );

				List<Picture> picturesMock = new ArrayList<>();
				pictures.add(mockPicture);
				pictures.add(mockPicture2);
				pictures.add(mockPicture3);
				pictures.add(mockPicture4);
				pictures.add(mockPicture5);
				pictures.add(mockPicture6);

				pictureRepository.save(mockPicture);
				pictureRepository.save(mockPicture2);
				pictureRepository.save(mockPicture3);
				pictureRepository.save(mockPicture4);
				pictureRepository.save(mockPicture5);
				pictureRepository.save(mockPicture6);



				Product mockProduct = new Product((long) i, "Sukienka modowa", new BigDecimal("300.00"), "PLN", "Sukienka na lato 2021", "L", "niebieski", "DaFashion Original Brand Design",
						"women", "koszulki", LocalDateTime.now(), random_int,picturesMock);

				if(i<30)
					mockProduct.setCategory("men");
				else if (i<60)
					mockProduct.setCategory("children");

					productService.save(mockProduct);
				mockPicture.setProduct(mockProduct);
				mockPicture2.setProduct(mockProduct);
				mockPicture3.setProduct(mockProduct);
				mockPicture4.setProduct(mockProduct);
				mockPicture5.setProduct(mockProduct);
				mockPicture6.setProduct(mockProduct);
				pictureRepository.save(mockPicture);
				pictureRepository.save(mockPicture2);
				pictureRepository.save(mockPicture3);
				pictureRepository.save(mockPicture4);
				pictureRepository.save(mockPicture5);
				pictureRepository.save(mockPicture6);


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
