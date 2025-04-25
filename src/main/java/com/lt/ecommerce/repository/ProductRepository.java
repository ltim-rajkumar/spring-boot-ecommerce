package com.lt.ecommerce.repository;

import com.lt.ecommerce.model.Product;
import com.lt.ecommerce.service.ConnectionService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductRepository {

    private static Connection connection = null;

    private void initConnection() throws SQLException {
        System.out.println("connection: " + connection);
        if (connection == null || connection.isClosed()) {
            connection = new ConnectionService().getConnection();
            System.out.println("connection created: " + connection);
        }
    }

    public boolean createProduct(Product product) throws SQLException {
        this.initConnection();
        String query = "INSERT INTO product VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, product.getId());
            preparedStatement.setInt(2, product.getShopId());
            preparedStatement.setString(3, product.getProductName());
            preparedStatement.setString(4, product.getProductDesc());
            preparedStatement.setString(5, product.getProductImage());
            preparedStatement.setDouble(6, product.getPrice());

            System.out.println("inserting product data to table: " + product);
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

    public Product getProduct(int productId) throws SQLException {
        this.initConnection();
        String query = "SELECT * FROM product WHERE id = ? ";
        try(PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, productId);
            ResultSet resultSet = preparedStatement.executeQuery();
            Product product = new Product();
            while (resultSet.next()) {
                product.setId(resultSet.getInt(1));
                product.setShopId(resultSet.getInt(2));
                product.setProductName(resultSet.getString(3));
                product.setProductDesc(resultSet.getString(4));
                product.setProductImage(resultSet.getString(5));
                product.setPrice(resultSet.getDouble(6));
            }
            return product;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            connection.close();
        }
        return null;
    }

    public ArrayList<Product> getAllProdcuts() throws SQLException {
        this.initConnection();
        String query = "SELECT * FROM product";
        try(PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            ArrayList<Product> products = new ArrayList<>();
            while (resultSet.next()) {
                Product product = new Product();
                product.setId(resultSet.getInt(1));
                product.setShopId(resultSet.getInt(2));
                product.setProductName(resultSet.getString(3));
                product.setProductDesc(resultSet.getString(4));
                product.setProductImage(resultSet.getString(5));
                product.setPrice(resultSet.getDouble(6));
                products.add(product);
            }
            return products;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            connection.close();
        }
        return null;
    }

    public boolean deleteProduct(int productId) throws SQLException {
        this.initConnection();
        String query = "DELETE FROM product WHERE id = ? ";

        try(PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, productId);
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

    public boolean deleteAllProducts() throws SQLException {
        this.initConnection();
        String query = "DELETE FROM product";

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
