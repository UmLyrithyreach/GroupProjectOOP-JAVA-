package Class;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/clothing_store";
    private static final String USER = "root";
    private static final String PASSWORD = "123abc";

    private static Connection connection;

    private DatabaseConnection() {}

    public static Connection getConnection() {
        if (connection  == null) {
            try {
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("Connected to database.");
            } catch (SQLException e) {
                System.out.println("Error connecting to database.");
                e.printStackTrace();
            }
        }
        return connection;
    }
}
