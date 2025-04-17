package com.hexaware.SIS.main;

import com.hexaware.SIS.util.DBConnUtil;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;

public class DBConnectionTest {
    public static void main(String[] args) {
        try (Connection conn = DBConnUtil.getConnection("db.properties")) {
            if (conn != null) {
                System.out.println("Connection established to the database!");

             
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM students");

                System.out.println("Sample Student Table Data:");
                while (rs.next()) {
                    System.out.println(""+ rs.getString("first_name") + " " + rs.getString("last_name"));
                }

            } else {
                System.out.println("Failed to connect to the database.");
            }
        } catch (Exception e) {
            System.err.println(" Exception during DB connection test: " + e.getMessage());
        }
    }
}
