package com.lt.ecommerce.controller;

import com.lt.ecommerce.service.ProductService;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.Scanner;

@RestController
public class ProductController {
    private static final Scanner scanner = new Scanner(System.in);

    public void run() throws SQLException {
        int option;
        do {
            System.out.println("---Choose order option---");
            System.out.println("1 Create order");
            System.out.println("2 Get order");
            System.out.println("3 Display all orders");
            System.out.println("4 Remove order");
            System.out.println("5 Remove all orders");
            System.out.println("---------------------------");
            System.out.println("9 Go to main menu");
            option = Integer.parseInt(scanner.nextLine());

            ProductService orderService = new ProductService();
            switch (option) {
                case 1: {
                    orderService.createProduct();
                    break;
                }
                case 2: {
                    orderService.getProduct(3001);
                    break;
                }
                case 3: {
                    orderService.displayAllProducts();
                    break;
                }
                case 4: {
                    orderService.deleteProduct(3001);
                    break;
                }
                case 5: {
                    orderService.deleteAllProducts();
                    break;
                }
            }
        }while (option != 9);
    }
}
