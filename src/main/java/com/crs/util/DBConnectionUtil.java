package com.crs.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectionUtil {

    private static final String URL = getEnvironmentValue(
            "CRS_DB_URL",
            "jdbc:mysql://localhost:3306/crs_db?useSSL=false&serverTimezone=UTC"
    );
    private static final String USERNAME = getEnvironmentValue("CRS_DB_USERNAME", "root");
    private static final String PASSWORD = System.getenv("CRS_DB_PASSWORD");

    private DBConnectionUtil() {
    }

    public static Connection getConnection() throws SQLException {
        if (PASSWORD == null || PASSWORD.isBlank()) {
            throw new SQLException("CRS_DB_PASSWORD environment variable is not configured.");
        }

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("MySQL JDBC Driver not found in runtime classpath.", e);
        }

        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }

    private static String getEnvironmentValue(String name, String defaultValue) {
        String value = System.getenv(name);
        return value == null || value.isBlank() ? defaultValue : value;
    }
}
