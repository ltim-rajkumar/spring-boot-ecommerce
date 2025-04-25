package com.lt.ecommerce.controller;

import com.lt.ecommerce.service.OrderDeliveryService;

import java.sql.SQLException;
import java.util.Scanner;

public class OrderDeliveryController {
    private static final Scanner scanner = new Scanner(System.in);

    public void run() throws SQLException {
        int option;
        do {
            System.out.println("---Choose order option---");
            System.out.println("1 Create order delivery");
            System.out.println("2 Get order delivery details");
            System.out.println("3 Display all order delivery details");
            System.out.println("4 Remove order delivery details");
            System.out.println("5 Remove all order delivery details");
            System.out.println("---------------------------");
            System.out.println("9 Go to main menu");
            option = Integer.parseInt(scanner.nextLine());

            OrderDeliveryService orderDeliveryService = new OrderDeliveryService();
            switch (option) {
                case 1: {
                    orderDeliveryService.createOrderDelivery();
                    break;
                }
                case 2: {
                    orderDeliveryService.getOrderDelivery(5001);
                    break;
                }
                case 3: {
                    orderDeliveryService.displayAllOrderDelivery();
                    break;
                }
                case 4: {
                    orderDeliveryService.deleteOrderDelivery(5001);
                    break;
                }
                case 5: {
                    orderDeliveryService.deleteAllOrderDeliverys();
                    break;
                }
            }
        }while (option != 9);
    }
}
