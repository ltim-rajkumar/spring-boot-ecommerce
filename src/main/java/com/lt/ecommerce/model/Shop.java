package com.lt.ecommerce.model;

import lombok.Data;

@Data
public class Shop {
    private int id;
    private int ownerId; // User.id
    private String name;
    private String address;
    private int contactNo;
    private String licenceNo;
}
