package com.lt.ecommerce.controller;

import com.lt.ecommerce.service.ShopService;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.Scanner;

@RestController
public class ShopController {
    private static final Scanner scanner = new Scanner(System.in);

    public void run() throws SQLException {
        int option;
        do {
            System.out.println("---Choose shop option---");
            System.out.println("1 Create shop");
            System.out.println("2 Get shop");
            System.out.println("3 Display all shops");
            System.out.println("4 Remove shop");
            System.out.println("5 Remove all shops");
            System.out.println("---------------------------");
            System.out.println("9 Go to main menu");
            option = Integer.parseInt(scanner.nextLine());

            ShopService shopService = new ShopService();
            switch (option) {
                case 1: {
                    shopService.createShop();
                    break;
                }
                case 2: {
                    shopService.getShop(4001);
                    break;
                }
                case 3: {
                    shopService.displayAllShops();
                    break;
                }
                case 4: {
                    shopService.removeShop(4001);
                    break;
                }
                case 5: {
                    shopService.removeAllShops();
                    break;
                }
            }
        }while (option != 9);
    }
}
