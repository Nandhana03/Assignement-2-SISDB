package com.hexaware.oms.service;

import com.hexaware.oms.entity.*;
import java.util.List;

public interface OrderService {
    void createOrder(User user, List<Product> products) throws Exception;
    void cancelOrder(int userId, int orderId) throws Exception;
    void createProduct(User user, Product product) throws Exception;
    void createUser(User user) throws Exception;
    List<Product> getAllProducts() throws Exception;
    List<Product> getOrderByUser(User user) throws Exception;
}
