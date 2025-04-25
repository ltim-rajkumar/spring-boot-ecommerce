package com.lt.ecommerce.service;

import com.lt.ecommerce.model.OrderDelivery;
import com.lt.ecommerce.repository.OrderDeliveryRepository;
import com.lt.ecommerce.util.OrderDeliveryStatus;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

@Service
@Data
public class OrderDeliveryService {
    @Autowired
    private OrderDeliveryRepository orderDeliveryRepository;

    public void createOrderDelivery() throws SQLException {
        OrderDelivery orderDelivery = new OrderDelivery();
        orderDelivery.setId(5001);
        orderDelivery.setOrderId(1001);
        orderDelivery.setDeliveryPersonId(2002);
        orderDelivery.setDeliveryStatus(OrderDeliveryStatus.PREPARING.ordinal());
        orderDelivery.setRemark("Preparing order");
        orderDelivery.setCreatedAt(LocalDateTime.now());
        orderDeliveryRepository.createOrderDelivery(orderDelivery);
    }

    public void getOrderDelivery(int id) throws SQLException {
        OrderDelivery orderDelivery = orderDeliveryRepository.getOrderDelivery(id);
        if(orderDelivery != null) {
            System.out.println("Order delivery details: " + orderDelivery);
        } else {
            System.out.println("No order delivery's found");
        }
    }

    public void displayAllOrderDelivery() throws SQLException {
        ArrayList<OrderDelivery> orderDeliverys = orderDeliveryRepository.getAllOrderDelivery();
        if (orderDeliverys != null && orderDeliverys.size() > 0) {
            orderDeliverys.forEach(( orderDelivery) -> {
                System.out.println("Order delivery details are: " + orderDelivery);
            });
        } else {
            System.out.println("No order delivery's found to display!!");
        }
    }

    public void deleteOrderDelivery(int id) throws SQLException {
        boolean isOrderDelete = orderDeliveryRepository.deleteOrderDelivery(id);
        if(isOrderDelete) {
            System.out.println("Order delivery's deleted successfully: "+ id);
        } else {
            System.out.println("No order delivery's found to delete!!");
        }
    }

    public void deleteAllOrderDeliverys() throws SQLException {
        boolean isOrderDelete = orderDeliveryRepository.deleteAllOrderDeliverys();
        if(isOrderDelete) {
            System.out.println("Order delivery's deleted successfully");
        } else {
            System.out.println("No order delivery's found to delete!!");
        }
    }

}
