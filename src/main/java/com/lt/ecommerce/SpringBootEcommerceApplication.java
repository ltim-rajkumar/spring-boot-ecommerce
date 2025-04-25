package com.lt.ecommerce;

import com.lt.ecommerce.configuration.AppConfig;
import com.lt.ecommerce.controller.*;
import java.sql.SQLException;
import java.util.Scanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class SpringBootEcommerceApplication {

	private static final Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) throws SQLException {

		SpringApplication.run(SpringBootEcommerceApplication.class, args);
		ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		SpringApplication.run(SpringBootEcommerceApplication.class, args);

		int option;
		do {
			System.out.println("--------Ecommerce--------");
			System.out.println("Please choose the option");
			System.out.println("1 Go to customer section");
			System.out.println("2 Go to shop section");
			System.out.println("3 Go to product section");
			System.out.println("4 Go to order section");
			System.out.println("5 Go to order delivery section");
			System.out.println("9 Exit");
			option = Integer.parseInt(scanner.nextLine());

			UserController userController = context.getBean(UserController.class);
			ShopController shopController = context.getBean(ShopController.class);
			ProductController productController = context.getBean(ProductController.class);
			OrderController orderController = context.getBean(OrderController.class);
			OrderDeliveryController orderDeliveryController = context.getBean(OrderDeliveryController.class);
//			CartController cartController = context.getBean(CartController.class);

			switch (option) {
				case 1: {
					userController.run();
					break;
				}
				case 2: {
					shopController.run();
					break;
				}
				case 3: {
					productController.run();
					break;
				}
				case 4: {
					orderController.run();
					break;
				}
				case 5: {
//                    cartController.run();
					break;
				}
				case 6: {
					orderDeliveryController.run();
					break;
				}
			}

		} while(option != 9);
	}
}
