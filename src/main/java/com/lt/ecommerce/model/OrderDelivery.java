package com.lt.ecommerce.model;

import com.lt.ecommerce.util.OrderDeliveryStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OrderDelivery {
    private int id;
    private int orderId;
    private int deliveryPersonId; // userId in User
    private OrderDeliveryStatus deliveryStatus;
    private String remark;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public void setDeliveryStatus(int anInt) {
    }
}
