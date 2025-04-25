package com.lt.ecommerce.service;
import com.lt.ecommerce.model.Product;
import com.lt.ecommerce.repository.ProductRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;

@Service
@Data
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public void createProduct() throws SQLException {
        Product product = new Product();
        product.setId(3001);
        product.setShopId(4001);
        product.setProductName("Laptop");
        product.setProductDesc("intel i10, 11th Gen, 64GB RAM, 2TB SSD");
        product.setProductImage("Image path");
        product.setPrice(79999.90);

        boolean isProductCreated = productRepository.createProduct(product);
        System.out.println("Product " +(isProductCreated ? " created!!!" : "failed to create"));
        System.out.println("product = " + product);
    }

    public void getProduct(int productId) throws SQLException {
        Product product = productRepository.getProduct(productId);
        if(product != null) {
            System.out.println("Product details: " + product);
        } else {
            System.out.println("No product's found to display!!");
        }
    }

    public void displayAllProducts() throws SQLException {
        ArrayList<Product> products = productRepository.getAllProdcuts();
        if (products != null && products.size() > 0) {
            products.forEach(( product) -> {
                System.out.println("Product details are: " + product);
            });
        } else {
            System.out.println("No product's found to display!!");
        }
    }

    public void deleteProduct(int productId) throws SQLException {
        boolean isProductDelete = productRepository.deleteProduct(productId);
        if(isProductDelete) {
            System.out.println("Product deleted successfully");
        } else {
            System.out.println("Product id: " + productId + " found to delete!!");
        }
    }

    public void deleteAllProducts() throws SQLException {
        boolean isProductDelete = productRepository.deleteAllProducts();
        if(isProductDelete) {
            System.out.println("All product's are deleted successfully");
        } else {
            System.out.println("No product's found to delete!!");
        }
    }

}
