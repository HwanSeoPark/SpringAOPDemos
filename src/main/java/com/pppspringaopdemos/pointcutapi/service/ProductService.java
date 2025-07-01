package com.pppspringaopdemos.pointcutapi.service;

public class ProductService {

    public String getProductById(String productId) {
        System.out.println(">>> Getting product by ID: " + productId);
        return "Product-" + productId;
    }

    public void getAllProducts() {
        System.out.println(">>> Getting all products");
    }

    public void updateStock(String productId, int quantity) {
        System.out.println(">>> Updating stock for " + productId + ": " + quantity);
    }
}
