package com.lt.ecommerce.repository;
import com.lt.ecommerce.model.OrderDelivery;
import com.lt.ecommerce.service.ConnectionService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDeliveryRepository {

    private static Connection connection = null;

    private void initConnection() throws SQLException {
        System.out.println("connection: " + connection);
        if (connection == null || connection.isClosed()) {
            connection = new ConnectionService().getConnection();
            System.out.println("connection created: " + connection);
        }
    }

    public boolean createOrderDelivery(OrderDelivery orderDelivery) throws SQLException {
        this.initConnection();
        String query = "INSERT INTO order_delivery VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, orderDelivery.getId());
            preparedStatement.setInt(2, orderDelivery.getOrderId());
            preparedStatement.setInt(3, orderDelivery.getDeliveryPersonId());
            preparedStatement.setInt(4, orderDelivery.getDeliveryStatus().ordinal());
            preparedStatement.setString(5, orderDelivery.getRemark());

            System.out.println("Inserting order delivery data to table: " + orderDelivery);
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

    public OrderDelivery getOrderDelivery(int id) throws SQLException {
        this.initConnection();
        String query = "SELECT * FROM order_delivery WHERE id = ? ";
        try(PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            OrderDelivery orderDelivery = new OrderDelivery();
            while (resultSet.next()) {
                orderDelivery.setId(resultSet.getInt(1));
                orderDelivery.setDeliveryPersonId(resultSet.getInt(2));
                orderDelivery.setDeliveryStatus(resultSet.getInt(3));
                orderDelivery.setRemark(resultSet.getString(4));
            }
            return orderDelivery;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            connection.close();
        }
        return null;
    }

    public ArrayList<OrderDelivery> getAllOrderDelivery() throws SQLException {
        this.initConnection();
        String query = "SELECT * FROM order_delivery";
        try(PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            ArrayList<OrderDelivery> orderDeliverys = new ArrayList<>();
            while (resultSet.next()) {
                OrderDelivery orderDelivery = new OrderDelivery();
                orderDelivery.setId(resultSet.getInt(1));
                orderDelivery.setDeliveryPersonId(resultSet.getInt(2));
                orderDelivery.setDeliveryStatus(resultSet.getInt(3));
                orderDelivery.setRemark(resultSet.getString(4));
                orderDeliverys.add(orderDelivery);
            }
            return orderDeliverys;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            connection.close();
        }
        return null;
    }

    public boolean deleteOrderDelivery(int id) throws SQLException {
        this.initConnection();
        String query = "DELETE FROM order_delivery WHERE id = ? ";

        try(PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
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

    public boolean deleteAllOrderDeliverys() throws SQLException {
        this.initConnection();
        String query = "DELETE FROM order_delivery";

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

