package com.lt.ecommerce.model;

import lombok.Data;

@Data
public class Order {
    private int id;
    private int customerId; // User.id
    private int productId; // Product.id
    private int qty;
    private double price;
    private double tax;
    private double finalAmount;
}
