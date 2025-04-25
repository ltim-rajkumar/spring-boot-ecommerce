package com.lt.ecommerce.service;
import com.lt.ecommerce.model.User;
import com.lt.ecommerce.repository.UserRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;

@Service
@Data
public class UserService {
    private final int userType;
    @Autowired
    private UserRepository userRepository;

    public UserService(int userType) {
        this.userType = userType;
    }

    public void createUser() throws SQLException {
        User user = new User();
        user.setUserType(this.userType);
        String userByType = this.getUserType(this.userType);
        user.setId(101);
        user.setFirstName("Jay");
        user.setLastName("Kumar");
        user.setMobileNo(991199001);
        user.setAddress("Pune");
        boolean isUserAdded = userRepository.addUser(user);
        System.out.println(userByType.toUpperCase() + (isUserAdded ? " created!!!" : "Failed to create"));
    }

    private String getUserType(int userType) {
        if(userType == 1) {
            return "customer";
        } else if(userType == 2) {
            return "seller";
        }
        return "delivery agent";
    }

    public ArrayList<User> getAllUsers() throws SQLException {
        return userRepository.getAllUsers();
    }

    public void getUser(int userId) throws SQLException {
        User user = userRepository.getUser(userId);
        if (user != null && user.getId() != 0) {
            System.out.println("User details = " + user.toString());
        } else {
            System.out.println("User id : " + userId + " does not exits!!!");
        }
    }

    public void removeUser(int userId) throws SQLException {
        boolean isUserDeleted = userRepository.removeUser(userId);
        if(isUserDeleted) {
            System.out.println("Removed  details = " + isUserDeleted);
        } else {
            System.out.println("User not found !!!");
        }
    }

    public void removeAllUsers() throws SQLException {
        boolean isUserDeleted = userRepository.removeAllUsers();
        if(isUserDeleted) {
            System.out.println("Removed  details = " + isUserDeleted);
        } else {
            System.out.println("User's not found !!!");
        }
    }

}
