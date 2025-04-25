package com.lt.ecommerce.controller;

import com.lt.ecommerce.service.UserService;
import com.lt.ecommerce.util.UserInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.Scanner;

@RestController
public class UserController {

    @Autowired
    private Scanner scanner;

    public void run() throws SQLException {
        int option = 0;
        do {
            System.out.println("---Choose user operation---");
            System.out.println("1 Create customer");
            System.out.println("2 Get customer");
            System.out.println("3 Display all customers");
            System.out.println("4 Remove customer");
            System.out.println("5 Remove all customers");
            System.out.println("---------------------------");
            System.out.println("9 Go to main menu");
            option = Integer.parseInt(scanner.nextLine());

            UserService userService = new UserService(UserInput.CUSTOMER.value);
            switch (option) {
                case 1: {
                    userService.createUser();
                    break;
                }
                case 2: {
                    userService.getUser(101);
                    break;
                }
                case 3: {
                    userService.getAllUsers();
                    break;
                }
                case 4: {
                    userService.removeUser(101);
                    break;
                }
                case 5: {
                    userService.removeAllUsers();
                    break;
                }
            }
        }while(option != 9);
    }
}
