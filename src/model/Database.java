package model;  // This must be present

import java.sql.*;

public class Database {
    private static final String URL = "jdbc:mysql://localhost:3306/atm_db"; 
    private static final String USER = "root";  
    private static final String PASSWORD = "psnl@8904";  

    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static void main(String[] args) {
        try {
            Connection conn = connect();
            if (conn != null) {
                System.out.println("✅ Connected to MySQL successfully!");
                conn.close();
            }
        } catch (SQLException e) {
            System.out.println("❌ Database connection failed!");
            e.printStackTrace();
        }
    }
}
