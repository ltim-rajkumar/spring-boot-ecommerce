package com.lt.ecommerce.service;
import com.lt.ecommerce.model.Shop;
import com.lt.ecommerce.repository.ShopRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;

@Service
@Data
public class ShopService {

    @Autowired
    private ShopRepository shopRepository;

    public void createShop() throws SQLException {
        Shop shop = new Shop();
        shop.setId(4001);
        shop.setOwnerId(2001);
        shop.setName("Flipkart");
        shop.setAddress("Pune");
        shop.setContactNo(923184180);
        shop.setName("Jay");
        boolean isShopCreated = shopRepository.addShop(shop);
        System.out.println("Shop " +(isShopCreated ? "created!!!" : "failed to create"));
        System.out.println("shop created = " + shop);
    }

    public void getShop(int shopId) throws SQLException {
        Shop shop = shopRepository.getShop(shopId);
        if(shop != null && shop.getId() != 0) {
            System.out.println("Shop details = " + shopRepository.getShop(shopId));
        } else {
            System.out.println("Shop id: " + shopId + " found to display!!");
        }
    }

    public void displayAllShops() throws SQLException {
        ArrayList<Shop> shops = (ArrayList<Shop>) shopRepository.getAllShops();
        if (shops != null && shops.size() > 0) {
            shops.stream().forEach(shop -> {
                System.out.println("Shop details = " + shop.toString());
            });
        } else {
            System.out.println("No shop's found to display!!");
        }
    }

    public void removeShop(int shopId) throws SQLException {
        boolean isShopDeleted = shopRepository.removeShop(shopId);
        if(isShopDeleted) {
            System.out.println("Shop deleted successfully");
        } else {
            System.out.println("Shop id: " + shopId + " found to remove!!");
        }
    }

    public void removeAllShops() throws SQLException {
        boolean isShopDeleted = shopRepository.removeAllShops();
        if(isShopDeleted) {
            System.out.println("Removed all shop's");
        } else {
            System.out.println("No shop's found to remove!!");
        }
    }
}
