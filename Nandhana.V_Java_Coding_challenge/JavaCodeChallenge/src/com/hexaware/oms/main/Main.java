package com.hexaware.oms.main;

import com.hexaware.oms.dao.*;

import com.hexaware.oms.entity.*;
import com.hexaware.oms.service.*;

import java.util.List;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class Main {


    public static void main(String[] args) {
    	
//    	Connection connection = null;
//    	try {
//    	    connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/omsdb", "root", "nankkrrii@2003");
//    	} catch (SQLException e) {
//    	    System.out.println("Connection failed: " + e.getMessage());
//    	    return;
//    	}

    	Properties props = new Properties();
    	try (InputStream input = new FileInputStream("db.properties")) {
    	    props.load(input);
    	    String url = props.getProperty("db.url");
    	    String username = props.getProperty("db.username");
    	    String password = props.getProperty("db.password");

    	    connection = DriverManager.getConnection(url, username, password);
    	} catch (IOException | SQLException e) {
    	    e.printStackTrace();
    	}

    	OrderProcessor orderProcessor = new OrderProcessor(connection);  
    	OrderService orderService = new OrderServiceImpl(orderProcessor);
        
        User adminUser = new User(1, "admin", "admin123", "admin");        
      
        User regularUser = new User(2, "john_doe", "password123", "user");
       
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n*** Order Management System ***");
            System.out.println("1. Create User");
            System.out.println("2. Create Product");
            System.out.println("3. Cancel Order");
            System.out.println("4. Get All Products");
            System.out.println("5. Get Orders by User");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            // Read user choice
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    // Create User
                    System.out.print("Enter username: ");
                    String username = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String password = scanner.nextLine();
                    System.out.print("Enter role (admin/user): ");
                    String role = scanner.nextLine();

                    // Create a new user object
                    User newUser = new User((int) (Math.random() * 1000), username, password, role);
                    try {
                        orderService.createUser(newUser);
                        System.out.println("User created successfully!");
                    } catch (Exception e) {
                        System.out.println("Error creating user: " + e.getMessage());
                    }
                    break;

                case 2:
                    // Create Product
                    System.out.print("Enter product name: ");
                    String productName = scanner.nextLine();
                    System.out.print("Enter product description: ");
                    String description = scanner.nextLine();
                    System.out.print("Enter product price: ");
                    double price = scanner.nextDouble();
                    System.out.print("Enter quantity in stock: ");
                    int quantityInStock = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter product type (Electronics/Clothing): ");
                    String type = scanner.nextLine();

                    // Create a new product object
                    Product product = null;
                    if ("Electronics".equalsIgnoreCase(type)) {
                        System.out.print("Enter brand: ");
                        String brand = scanner.nextLine();
                        System.out.print("Enter warranty period (in months): ");
                        int warrantyPeriod = scanner.nextInt();
                        product = new Electronics((int) (Math.random() * 1000), productName, description, price, quantityInStock, type, brand, warrantyPeriod);
                    } else if ("Clothing".equalsIgnoreCase(type)) {
                        System.out.print("Enter size: ");
                        String size = scanner.nextLine();
                        System.out.print("Enter color: ");
                        String color = scanner.nextLine();
                        product = new Clothing((int) (Math.random() * 1000), productName, description, price, quantityInStock, type, size, color);
                    }

                    if (product != null) {
                        try {
                            orderService.createProduct(adminUser, product);
                            System.out.println("Product created successfully!");
                        } catch (Exception e) {
                            System.out.println("Error creating product: " + e.getMessage());
                        }
                    }
                    break;

                case 3:
                    // Cancel Order
                    System.out.print("Enter user ID: ");
                    int userId = scanner.nextInt();
                    System.out.print("Enter order ID to cancel: ");
                    int orderId = scanner.nextInt();
                    try {
                        orderService.cancelOrder(userId, orderId);
                        System.out.println("Order canceled successfully!");
                    } catch (Exception e) {
                        System.out.println("Error canceling order: " + e.getMessage());
                    }
                    break;

                case 4:
                    // Get All Products
                    try {
                        List<Product> products = orderService.getAllProducts();
                        System.out.println("Available Products:");
                        for (Product p : products) {
                            System.out.println("ID: " + p.getProductId() + ", Name: " + p.getProductName() + ", Price: $" + p.getPrice() + ", Quantity: " + p.getQuantityInStock());
                        }
                    } catch (Exception e) {
                        System.out.println("Error retrieving products: " + e.getMessage());
                    }
                    break;

                case 5:
                    // Get Orders by User
                    System.out.print("Enter user ID to fetch orders: ");
                    int orderUserId = scanner.nextInt();
                    User userOrders = new User(orderUserId, "User" + orderUserId, "password", "user"); 
                    try {
                        List<Product> orderedProducts = orderService.getOrderByUser(userOrders);
                        System.out.println("Orders for user " + userOrders.getUsername() + ":");
                        for (Product p : orderedProducts) {
                            System.out.println("Product ID: " + p.getProductId() + ", Name: " + p.getProductName());
                        }
                    } catch (Exception e) {
                        System.out.println("Error retrieving orders: " + e.getMessage());
                    }
                    break;

                case 6:
                    System.out.println("Exiting system. Goodbye!");
                    break;

                default:
                    System.out.println("Invalid choice! Please try again.");
                    break;
            }
        } while (choice != 6); 

        scanner.close(); 
    }
}

//package com.hexaware.oms.main;
//
//import com.hexaware.oms.dao.*;
//import com.hexaware.oms.entity.*;
//import com.hexaware.oms.service.*;
//
//import java.sql.*;
//import java.util.List;
//import java.util.Scanner;
//
//public class Main {
//
//    public static void main(String[] args) {
//        Connection connection = null;
//        Statement stmt = null;
//        try {
//            // Load the JDBC driver (MySQL in this case)
//            Class.forName("com.mysql.cj.jdbc.Driver");
//
//            // Establish the connection to the database
//            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/omsdb", "root", "nankkrrii@2003");
//
//            // Create the Statement object to execute SQL queries
//            stmt = connection.createStatement();
//
//            // Initialize the DAO and Service classes
//            OrderProcessor orderProcessor = new OrderProcessor();  // Correct DAO class
//            OrderService orderService = new OrderServiceImpl(orderProcessor);  // Correct service class
//
//            // Sample Admin user for testing
//            User adminUser = new User(1, "admin", "admin123", "admin");
//
//            // Sample regular user for testing
//            User regularUser = new User(2, "john_doe", "password123", "user");
//
//            // Initialize Scanner to read user input
//            Scanner scanner = new Scanner(System.in);
//            int choice;
//
//            // Display the Menu in a loop
//            do {
//                System.out.println("\n*** Order Management System ***");
//                System.out.println("1. Create User");
//                System.out.println("2. Create Product");
//                System.out.println("3. Cancel Order");
//                System.out.println("4. Get All Products");
//                System.out.println("5. Get Orders by User");
//                System.out.println("6. Exit");
//                System.out.print("Enter your choice: ");
//
//                // Read user choice
//                choice = scanner.nextInt();
//                scanner.nextLine(); // Consume the newline character
//
//                switch (choice) {
//                    case 1:
//                        // Create User
//                        System.out.print("Enter username: ");
//                        String username = scanner.nextLine();
//                        System.out.print("Enter password: ");
//                        String password = scanner.nextLine();
//                        System.out.print("Enter role (admin/user): ");
//                        String role = scanner.nextLine();
//
//                        // Create a new user object
//                        User newUser = new User((int) (Math.random() * 1000), username, password, role);
//                        try {
//                            orderService.createUser(newUser);
//                            System.out.println("User created successfully!");
//                        } catch (Exception e) {
//                            System.out.println("Error creating user: " + e.getMessage());
//                        }
//                        break;
//
//                    case 2:
//                        // Create Product
//                        System.out.print("Enter product name: ");
//                        String productName = scanner.nextLine();
//                        System.out.print("Enter product description: ");
//                        String description = scanner.nextLine();
//                        System.out.print("Enter product price: ");
//                        double price = scanner.nextDouble();
//                        System.out.print("Enter quantity in stock: ");
//                        int quantityInStock = scanner.nextInt();
//                        scanner.nextLine(); // Consume newline
//                        System.out.print("Enter product type (Electronics/Clothing): ");
//                        String type = scanner.nextLine();
//
//                        // Create a new product object
//                        Product product = null;
//                        if ("Electronics".equalsIgnoreCase(type)) {
//                            System.out.print("Enter brand: ");
//                            String brand = scanner.nextLine();
//                            System.out.print("Enter warranty period (in months): ");
//                            int warrantyPeriod = scanner.nextInt();
//                            product = new Electronics((int) (Math.random() * 1000), productName, description, price, quantityInStock, type, brand, warrantyPeriod);
//                        } else if ("Clothing".equalsIgnoreCase(type)) {
//                            System.out.print("Enter size: ");
//                            String size = scanner.nextLine();
//                            System.out.print("Enter color: ");
//                            String color = scanner.nextLine();
//                            product = new Clothing((int) (Math.random() * 1000), productName, description, price, quantityInStock, type, size, color);
//                        }
//
//                        if (product != null) {
//                            try {
//                                orderService.createProduct(adminUser, product);
//                                System.out.println("Product created successfully!");
//                            } catch (Exception e) {
//                                System.out.println("Error creating product: " + e.getMessage());
//                            }
//                        }
//                        break;
//
//                    case 3:
//                        // Cancel Order
//                        System.out.print("Enter user ID: ");
//                        int userId = scanner.nextInt();
//                        System.out.print("Enter order ID to cancel: ");
//                        int orderId = scanner.nextInt();
//                        try {
//                            orderService.cancelOrder(userId, orderId);
//                            System.out.println("Order canceled successfully!");
//                        } catch (Exception e) {
//                            System.out.println("Error canceling order: " + e.getMessage());
//                        }
//                        break;
//
//                    case 4:
//                        // Get All Products
//                        try {
//                            List<Product> products = orderService.getAllProducts();
//                            System.out.println("Available Products:");
//                            for (Product p : products) {
//                                System.out.println("ID: " + p.getProductId() + ", Name: " + p.getProductName() + ", Price: $" + p.getPrice() + ", Quantity: " + p.getQuantityInStock());
//                            }
//                        } catch (Exception e) {
//                            System.out.println("Error retrieving products: " + e.getMessage());
//                        }
//                        break;
//
//                    case 5:
//                        // Get Orders by User
//                        System.out.print("Enter user ID to fetch orders: ");
//                        int orderUserId = scanner.nextInt();
//                        User userOrders = new User(orderUserId, "User" + orderUserId, "password", "user"); // Dummy user for testing
//                        try {
//                            List<Product> orderedProducts = orderService.getOrderByUser(userOrders);
//                            System.out.println("Orders for user " + userOrders.getUsername() + ":");
//                            for (Product p : orderedProducts) {
//                                System.out.println("Product ID: " + p.getProductId() + ", Name: " + p.getProductName());
//                            }
//                        } catch (Exception e) {
//                            System.out.println("Error retrieving orders: " + e.getMessage());
//                        }
//                        break;
//
//                    case 6:
//                        System.out.println("Exiting system. Goodbye!");
//                        break;
//
//                    default:
//                        System.out.println("Invalid choice! Please try again.");
//                        break;
//                }
//            } while (choice != 6); // Continue the loop until user chooses to exit
//
//            scanner.close(); // Close the scanner resource
//
//        } catch (SQLException | ClassNotFoundException e) {
//            System.out.println("Error: " + e.getMessage());
//        } finally {
//            // Clean up database resources
//            try {
//                if (stmt != null) {
//                    stmt.close();
//                }
//                if (connection != null) {
//                    connection.close();
//                }
//            } catch (SQLException e) {
//                System.out.println("Error closing resources: " + e.getMessage());
//            }
//        }
//    }
//}
