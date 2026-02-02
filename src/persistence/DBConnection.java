package persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/stockdb?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";            // your MySQL username
    private static final String PASSWORD = "123456789"; // your MySQL password

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.out.println("Database Connection Failed!");
            return null;
        }
    }
}
