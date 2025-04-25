package com.lt.ecommerce.util;

public class CommonUtil {

    static enum OrderDeliveryStatus {
        PENDING,
        ORDERED,
        IN_TRANSIT,
        DELIVERED,
        DELIVERY_FAILED,
        CANCELLED
    }
}
