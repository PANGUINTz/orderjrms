package com.example.orderjms;

public interface CustomerOrderRepository {
    void updateOrderAmount(String from, String product, int amount);
}
