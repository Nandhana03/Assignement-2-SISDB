package com.hexaware.oms.dao;

import com.hexaware.oms.entity.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderProcessor implements IOrderManagementRepository {

    private Connection connection;
//    private static final String DB_PROPS = "db.properties";

    public OrderProcessor(Connection connection) {
        this.connection = connection;
    }


    @Override
    public void createOrder(User user, List<Product> products) throws SQLException {
        String insertOrderSQL = "INSERT INTO orders (userId) VALUES (?)";
        try (PreparedStatement orderStatement = connection.prepareStatement(insertOrderSQL, Statement.RETURN_GENERATED_KEYS)) {
            orderStatement.setInt(1, user.getUserId());
            int affectedRows = orderStatement.executeUpdate();
            if (affectedRows > 0) {
                ResultSet generatedKeys = orderStatement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int orderId = generatedKeys.getInt(1);
                    createOrderItems(orderId, products);
                }
            } else {
                throw new SQLException("Creating order failed, no rows affected.");
            }
        }
    }

    private void createOrderItems(int orderId, List<Product> products) throws SQLException {
        String insertOrderItemSQL = "INSERT INTO order_items (orderId, productId, quantity) VALUES (?, ?, ?)";
        try (PreparedStatement itemStatement = connection.prepareStatement(insertOrderItemSQL)) {
            for (Product product : products) {
                itemStatement.setInt(1, orderId);
                itemStatement.setInt(2, product.getProductId());
                itemStatement.setInt(3, product.getQuantityInStock());
                itemStatement.addBatch();
            }
            itemStatement.executeBatch();
        }
    }

    @Override
    public void cancelOrder(int userId, int orderId) throws SQLException {
        String cancelSQL = "DELETE oi, o FROM order_items oi " +
                           "JOIN orders o ON oi.orderId = o.orderId " +
                           "WHERE o.userId = ? AND o.orderId = ?";
        try (PreparedStatement cancelStatement = connection.prepareStatement(cancelSQL)) {
            cancelStatement.setInt(1, userId);
            cancelStatement.setInt(2, orderId);
            cancelStatement.executeUpdate();
        }
    }

    @Override
    public void createProduct(User user, Product product) throws SQLException {
        String insertProductSQL = "INSERT INTO products (productName, description, price, quantityInStock, type) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement productStatement = connection.prepareStatement(insertProductSQL, Statement.RETURN_GENERATED_KEYS)) {
            productStatement.setString(1, product.getProductName());
            productStatement.setString(2, product.getDescription());
            productStatement.setDouble(3, product.getPrice());
            productStatement.setInt(4, product.getQuantityInStock());
            productStatement.setString(5, product.getType());

            int affectedRows = productStatement.executeUpdate();
            if (affectedRows > 0) {
                ResultSet generatedKeys = productStatement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int productId = generatedKeys.getInt(1);
                    if ("Electronics".equals(product.getType())) {
                        createElectronicsSubclass(productId, (Electronics) product);
                    } else if ("Clothing".equals(product.getType())) {
                        createClothingSubclass(productId, (Clothing) product);
                    }
                }
            } else {
                throw new SQLException("Creating product failed, no rows affected.");
            }
        }
    }

    private void createElectronicsSubclass(int productId, Electronics electronics) throws SQLException {
        String insertElectronicsSQL = "INSERT INTO electronics (productId, brand, warrantyPeriod) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(insertElectronicsSQL)) {
            statement.setInt(1, productId);
            statement.setString(2, electronics.getBrand());
            statement.setInt(3, electronics.getWarrantyPeriod());
            statement.executeUpdate();
        }
    }

    private void createClothingSubclass(int productId, Clothing clothing) throws SQLException {
        String insertClothingSQL = "INSERT INTO clothing (productId, size, color) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(insertClothingSQL)) {
            statement.setInt(1, productId);
            statement.setString(2, clothing.getSize());
            statement.setString(3, clothing.getColor());
            statement.executeUpdate();
        }
    }

    @Override
    public void createUser(User user) throws SQLException {
        String insertUserSQL = "INSERT INTO users (username, password, role) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(insertUserSQL)) {
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getRole());
            statement.executeUpdate();
        }
    }

    @Override
    public List<Product> getAllProducts() throws SQLException {
        List<Product> products = new ArrayList<>();
        String getAllProductsSQL = "SELECT * FROM products";
        try (PreparedStatement statement = connection.prepareStatement(getAllProductsSQL);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                int productId = resultSet.getInt("productId");
                String productName = resultSet.getString("productName");
                String description = resultSet.getString("description");
                double price = resultSet.getDouble("price");
                int quantityInStock = resultSet.getInt("quantityInStock");
                String type = resultSet.getString("type");

                Product product;
                if ("Electronics".equals(type)) {
                    product = getElectronicsById(productId);
                } else if ("Clothing".equals(type)) {
                    product = getClothingById(productId);
                } else {
                    product = new Product(productId, productName, description, price, quantityInStock, type);
                }
                products.add(product);
            }
        }
        return products;
    }

    private Electronics getElectronicsById(int productId) throws SQLException {
        String getElectronicsSQL = "SELECT * FROM electronics WHERE productId = ?";
        try (PreparedStatement statement = connection.prepareStatement(getElectronicsSQL)) {
            statement.setInt(1, productId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String brand = resultSet.getString("brand");
                    int warrantyPeriod = resultSet.getInt("warrantyPeriod");
                    return new Electronics(productId, null, null, 0, 0, null, brand, warrantyPeriod);
                }
            }
        }
        return null;
    }

    private Clothing getClothingById(int productId) throws SQLException {
        String getClothingSQL = "SELECT * FROM clothing WHERE productId = ?";
        try (PreparedStatement statement = connection.prepareStatement(getClothingSQL)) {
            statement.setInt(1, productId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String size = resultSet.getString("size");
                    String color = resultSet.getString("color");
                    return new Clothing(productId, null, null, 0, 0, null, size, color);
                }
            }
        }
        return null;
    }

    @Override
    public List<Product> getOrderByUser(User user) throws SQLException {
        List<Product> products = new ArrayList<>();
        String getOrderSQL = "SELECT p.* FROM products p " +
                             "JOIN order_items oi ON p.productId = oi.productId " +
                             "JOIN orders o ON oi.orderId = o.orderId " +
                             "WHERE o.userId = ?";
        try (PreparedStatement statement = connection.prepareStatement(getOrderSQL)) {
            statement.setInt(1, user.getUserId());
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    int productId = resultSet.getInt("productId");
                    String productName = resultSet.getString("productName");
                    String description = resultSet.getString("description");
                    double price = resultSet.getDouble("price");
                    int quantityInStock = resultSet.getInt("quantityInStock");
                    String type = resultSet.getString("type");

                    Product product = new Product(productId, productName, description, price, quantityInStock, type);
                    products.add(product);
                }
            }
        }
        return products;
    }
}

