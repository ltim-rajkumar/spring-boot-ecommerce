package com.lt.ecommerce.repository;

import com.lt.ecommerce.model.Shop;
import com.lt.ecommerce.service.ConnectionService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ShopRepository {

    private static Connection connection = null;

    private void initConnection() throws SQLException {
        System.out.println("connection: " + connection);
        if (connection == null || connection.isClosed()) {
            connection = new ConnectionService().getConnection();
            System.out.println("connection created: " + connection);
        }
    }

    public boolean addShop(Shop shop) throws SQLException {
        this.initConnection();
        String query = "INSERT INTO shop VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, shop.getId());
            preparedStatement.setInt(2, shop.getOwnerId());
            preparedStatement.setString(3, shop.getName());
            preparedStatement.setString(4, shop.getAddress());
            preparedStatement.setInt(5, shop.getContactNo());
            preparedStatement.setString(6, shop.getLicenceNo());

            System.out.println("inserting shop data to table: " + shop);

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

    public Shop getShop(int shopId)  throws SQLException {
        this.initConnection();
        String query = "SELECT * FROM shop WHERE id = ? ";
        try(PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, shopId);
            ResultSet resultSet = preparedStatement.executeQuery();
            Shop shop = new Shop();
            while (resultSet.next()) {
                shop.setId(resultSet.getInt(1));
                shop.setOwnerId(resultSet.getInt(2));
                shop.setName(resultSet.getString(3));
                shop.setAddress(resultSet.getString(4));
                shop.setContactNo(resultSet.getInt(5));
                shop.setLicenceNo(resultSet.getString(6));
            }
            return shop;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            connection.close();
        }
        return null;
    }

    public ArrayList<Shop> getAllShops()  throws SQLException {
        this.initConnection();
        ArrayList<Shop> shops = new ArrayList<>();
        String query = "SELECT * FROM shop;";
        try(PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Shop shop = new Shop();
                shop.setId(resultSet.getInt(1));
                shop.setOwnerId(resultSet.getInt(2));
                shop.setName(resultSet.getString(3));
                shop.setAddress(resultSet.getString(4));
                shop.setContactNo(resultSet.getInt(5));
                shop.setLicenceNo(resultSet.getString(6));
                shops.add(shop);
            }
            return shops;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            connection.close();
        }
        return shops;
    }

    public boolean removeShop(int shopId) throws SQLException {
        this.initConnection();
        String query = "DELETE FROM shop WHERE id = ? ";

        try(PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, shopId);
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

    public boolean removeAllShops()throws SQLException {
        this.initConnection();
        String query = "DELETE FROM shop;";

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
