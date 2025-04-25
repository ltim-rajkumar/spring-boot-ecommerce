package com.lt.ecommerce.model;

import lombok.Data;

@Data
public class Product {
    private int id;
    private int shopId;
    private String productName;
    private String productDesc;
    private String productImage;
    private double price;
}
