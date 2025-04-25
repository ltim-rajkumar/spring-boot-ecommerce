package com.lt.ecommerce.repository;
import com.lt.ecommerce.model.Order;
import com.lt.ecommerce.service.ConnectionService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderRepository {

    private static Connection connection = null;

    private void initConnection() throws SQLException {
        System.out.println("connection: " + connection);
        if (connection == null || connection.isClosed()) {
            connection = new ConnectionService().getConnection();
            System.out.println("connection created: " + connection);
        }
    }

    public boolean createOrder(Order order) throws SQLException {
        this.initConnection();
        String query = "INSERT INTO order VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, order.getId());
            preparedStatement.setInt(2, order.getCustomerId());
            preparedStatement.setInt(3, order.getProductId());
            preparedStatement.setInt(4, order.getQty());
            preparedStatement.setDouble(5, order.getPrice());
            preparedStatement.setDouble(6, order.getTax());
            preparedStatement.setDouble(7, order.getFinalAmount());

            System.out.println("Inserting order data to table: " + order);
            int rowsInserted = preparedStatement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            connection.close();
        }
        return false;
    }

    public Order getOrder(int orderId) throws SQLException {
        this.initConnection();
        String query = "SELECT * FROM order WHERE id = ? ";
        try(PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, orderId);
            ResultSet resultSet = preparedStatement.executeQuery();
            Order order = new Order();
            while (resultSet.next()) {
                order.setId(resultSet.getInt(1));
                order.setCustomerId(resultSet.getInt(2));
                order.setProductId(resultSet.getInt(3));
                order.setQty(resultSet.getInt(4));
                order.setPrice(resultSet.getDouble(5));
                order.setTax(resultSet.getDouble(6));
                order.setFinalAmount(resultSet.getDouble(7));
            }
            return order;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            connection.close();
        }
        return null;
    }

    public ArrayList<Order> getAllOrders() throws SQLException {
        this.initConnection();
        String query = "SELECT * FROM order";
        try(PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ArrayList<Order> orders = new ArrayList<>();
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Order order = new Order();
                order.setId(resultSet.getInt(1));
                order.setCustomerId(resultSet.getInt(2));
                order.setProductId(resultSet.getInt(3));
                order.setQty(resultSet.getInt(4));
                order.setPrice(resultSet.getDouble(5));
                order.setTax(resultSet.getDouble(6));
                order.setFinalAmount(resultSet.getDouble(7));
                orders.add(order);
            }
            return orders;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            connection.close();
        }
        return null;
    }

    public boolean deleteOrder(int orderId) throws SQLException {
        this.initConnection();
        String query = "DELETE FROM order WHERE id = ? ";

        try(PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, orderId);
            int isDelete =  preparedStatement.executeUpdate();
            return isDelete > 0;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            connection.close();
        }
        return false;
    }

    public boolean deleteAllOrders() throws SQLException {
        this.initConnection();
        String query = "DELETE FROM order";

        try(PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            int isDelete =  preparedStatement.executeUpdate();
            return isDelete > 0;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            connection.close();
        }
        return false;
    }

}
