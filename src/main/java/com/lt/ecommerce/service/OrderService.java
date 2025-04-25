package com.lt.ecommerce.service;

import com.lt.ecommerce.model.Order;
import com.lt.ecommerce.repository.OrderRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;

@Service
@Data
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public void createOrder() throws SQLException {
        Order order = new Order();
        order.setId(1001);
        order.setCustomerId(2001);
        order.setProductId(3001);
        order.setPrice(122.3f);
        order.setQty(3);
        order.setTax(15);
        order.setFinalAmount(525);

        boolean isProductCreated = orderRepository.createOrder(order);
        System.out.println("Order delivery details " +(isProductCreated ? "created!!!" : "failed to create"));
        System.out.println("Order delivery details = " + order);
    }

    public void getOrder(int orderId) throws SQLException {
        Order order = orderRepository.getOrder(orderId);
        if(order != null) {
            System.out.println("Order details: " + order);
        }
    }

    public void displayAllOrders() throws SQLException {
        ArrayList<Order> orders = orderRepository.getAllOrders();
        if (orders != null && orders.size() > 0) {
            orders.forEach(( order) -> {
                System.out.println("Order details are: " + order);
            });
        } else {
            System.out.println("No order's found to display!!");
        }
    }

    public void deleteOrder(int orderId) throws SQLException {
        boolean isOrderDelete = orderRepository.deleteOrder(orderId);
        if(isOrderDelete) {
            System.out.println("Order deleted successfully");
        } else {
            System.out.println("Order id: " + orderId + " found to delete!!");
        }
    }

    public void deleteAllOrders() throws SQLException {
        boolean isOrderDelete = orderRepository.deleteAllOrders();
        if(isOrderDelete) {
            System.out.println("Removed all order's");
        } else {
            System.out.println("No order's found to delete!!");
        }
    }
}
