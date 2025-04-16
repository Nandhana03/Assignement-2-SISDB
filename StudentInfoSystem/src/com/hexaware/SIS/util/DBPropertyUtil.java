package com.hexaware.SIS.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class DBPropertyUtil {

    public static Properties getConnectionProperties(String filename) {
        Properties props = new Properties();
        try (FileInputStream fis = new FileInputStream(filename)) {
            props.load(fis);
        } catch (IOException e) {
            System.err.println("Error reading property file: " + e.getMessage());
        }
        return props;
    }
}
