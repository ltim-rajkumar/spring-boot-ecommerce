package com.lt.ecommerce.model;

import lombok.Data;
//import lombok.Getter;
//import lombok.Setter;
//import lombok.ToString;

//@Getter
//@Setter
//@ToString
@Data
public class User {
    private int id;
    private String firstName;
    private String lastName;
    private int mobileNo;
    private String address;
    private int userType;
    private String createdAt;
}
