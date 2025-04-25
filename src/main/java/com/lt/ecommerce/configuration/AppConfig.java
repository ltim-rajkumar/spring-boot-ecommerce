package com.lt.ecommerce.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.Scanner;

@Configuration
//@ComponentScan(basePackages = "com.lt.ecommerce")
@ComponentScan("com.lt.ecommerce")
public class AppConfig {
    @Bean
    public Scanner scanner() {
        return new Scanner(System.in);
    }
}