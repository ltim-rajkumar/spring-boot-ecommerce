package com.lt.ecommerce.repository;

import com.lt.ecommerce.model.User;
import com.lt.ecommerce.service.ConnectionService;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class UserRepository {

    private static Connection connection = null;

    private void initConnection() throws SQLException {
        System.out.println("connection: " + connection);
        if (connection == null || connection.isClosed()) {
            connection = new ConnectionService().getConnection();
            System.out.println("connection created: " + connection);
        }
    }

    public boolean addUser(User user) throws SQLException {
        this.initConnection();
        String query = "INSERT INTO users VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, user.getId());
            preparedStatement.setString(2, user.getFirstName());
            preparedStatement.setString(3, user.getLastName());
            preparedStatement.setInt(4, user.getMobileNo());
            preparedStatement.setString(5, user.getAddress());
            preparedStatement.setInt(6, user.getUserType());
            preparedStatement.setString(7, String.valueOf(LocalDateTime.now()));

            System.out.println("inserting user data to table: " + user);

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

    public User getUser(int userId) throws SQLException {
        this.initConnection();
        String query = "SELECT * FROM users WHERE id = ? ";
        try(PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            User user = new User();
            while (resultSet.next()) {
                user.setId(resultSet.getInt(1));
                user.setFirstName(resultSet.getString(2));
                user.setLastName(resultSet.getString(3));
                user.setMobileNo(resultSet.getInt(4));
                user.setAddress(resultSet.getString(5));
                user.setUserType(resultSet.getInt(6));
                user.setCreatedAt(resultSet.getString(7));
            }
            return user;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            connection.close();
        }
        return null;
    }

    public ArrayList<User> getAllUsers() throws SQLException {
        this.initConnection();
        ArrayList<User> users = new ArrayList<>();
        String query = "SELECT * FROM users;";
        try(PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt(1));
                user.setFirstName(resultSet.getString(2));
                user.setLastName(resultSet.getString(3));
                user.setMobileNo(resultSet.getInt(4));
                user.setAddress(resultSet.getString(5));
                user.setUserType(resultSet.getInt(6));
                user.setCreatedAt(resultSet.getString(7));
                users.add(user);
            }
            return users;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            connection.close();
        }
        return users;
    }

    public boolean removeUser(int userId) throws SQLException {
        this.initConnection();
        String query = "DELETE FROM users WHERE id = ? ";

        try(PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, userId);
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

    public boolean removeAllUsers() throws SQLException {
        this.initConnection();
        String query = "DELETE FROM users;";

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

    public boolean updateUser(int id, String firstName, String lastName, String mobileNo, String address) throws SQLException {
        this.initConnection();
        String query = "UPDATE users SET firstName = ?, lastName = ?, mobileNo = ?, address = ? WHERE id = ?";
        try (Connection connection = new ConnectionService().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            preparedStatement.setString(3, mobileNo);
            preparedStatement.setString(4, address);
            preparedStatement.setInt(5, id);

            return preparedStatement.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new RuntimeException("Error updating user: " + e.getMessage(), e);
        }
    }

}
