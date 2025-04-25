package com.lt.ecommerce.util;

public enum UserInput {
    CUSTOMER,
    DELIVERY_AGENT,
    PRODUCT,
    ORDER;
    public final int value = 1 + ordinal();
}