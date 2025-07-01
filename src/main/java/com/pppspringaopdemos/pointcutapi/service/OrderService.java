package com.pppspringaopdemos.pointcutapi.service;

import java.util.UUID;

public class OrderService {

    @Auditable("create-order")
    public String createOrder(String userId, String productId, int quantity) {
        String orderId = UUID.randomUUID().toString();
        System.out.println(">>> [OrderService] Created order " + orderId +
                " for user=" + userId +
                ", product=" + productId +
                ", quantity=" + quantity);
        return orderId;
    }

    @Auditable("cancel-order")
    public void cancelOrder(String orderId) {
        System.out.println(">>> [OrderService] Cancelled order " + orderId);
    }

    public void getOrderStatus(String orderId) {
        System.out.println(">>> [OrderService] Status of order " + orderId + ": SHIPPING");
    }
}