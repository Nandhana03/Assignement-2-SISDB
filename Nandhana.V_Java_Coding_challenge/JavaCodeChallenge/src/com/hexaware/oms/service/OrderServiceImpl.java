package com.hexaware.oms.service;

import com.hexaware.oms.dao.OrderProcessor;
import com.hexaware.oms.entity.*;
import com.hexaware.oms.exception.*;

import java.util.List;

public class OrderServiceImpl implements OrderService {

    private final OrderProcessor orderProcessor;

    // Constructor to inject the OrderProcessor (DAO)
    public OrderServiceImpl(OrderProcessor orderProcessor) {
        this.orderProcessor = orderProcessor;
    }

    @Override
    public void createOrder(User user, List<Product> products) throws Exception {
        // Validate business rules
        if (products.isEmpty()) {
            throw new IllegalArgumentException("Product list cannot be empty.");
        }
        orderProcessor.createOrder(user, products);
    }

    @Override
    public void cancelOrder(int userId, int orderId) throws Exception {
        // Check if order ID and user ID are valid
        if (orderId <= 0 || userId <= 0) {
            throw new IllegalArgumentException("Invalid orderId or userId.");
        }
        orderProcessor.cancelOrder(userId, orderId);
    }

    @Override
    public void createProduct(User user, Product product) throws Exception {
        // Check if the user is authorized (e.g., an admin)
        if (!"admin".equals(user.getRole())) {
            throw new UnauthorizedUserException("Only admins can create products.");
        }
        orderProcessor.createProduct(user, product);
    }

    @Override
    public void createUser(User user) throws Exception {
        // Validate user data (e.g., username)
        if (user.getUsername() == null || user.getUsername().isEmpty()) {
            throw new IllegalArgumentException("Username cannot be empty.");
        }
        orderProcessor.createUser(user);
    }

    @Override
    public List<Product> getAllProducts() throws Exception {
        return orderProcessor.getAllProducts();
    }

    @Override
    public List<Product> getOrderByUser(User user) throws Exception {
        return orderProcessor.getOrderByUser(user);
    }
}
