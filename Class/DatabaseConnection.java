package Class;

import java.sql.*;

public class DatabaseConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/clothing_store";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "rootAdmin123";

    // Establish a new connection for each query execution
    static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }

    // Execute a SELECT query
    public static ResultSet executeQuery(String query) {
        try (Connection conn = getConnection();
             Statement statement = conn.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            return resultSet;  // The caller must handle the ResultSet properly

        } catch (SQLException e) {
            System.out.println("Query execution failed: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    // Execute an INSERT, UPDATE, or DELETE query
    public static int executeUpdate(String query) {
        try (Connection conn = getConnection();
             Statement statement = conn.createStatement()) {

            return statement.executeUpdate(query);

        } catch (SQLException e) {
            System.out.println("Update execution failed: " + e.getMessage());
            e.printStackTrace();
        }
        return 0;
    }

    // Execute a parameterized query (Prevents SQL Injection)
    public static ResultSet executePreparedQuery(String query, Object... params) {
        try (Connection conn = getConnection();
             PreparedStatement statement = conn.prepareStatement(query)) {

            // Set all parameters dynamically
            for (int i = 0; i < params.length; i++) {
                statement.setObject(i + 1, params[i]);
            }
            return statement.executeQuery();

        } catch (SQLException e) {
            System.out.println("Prepared query execution failed: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    // Execute an INSERT, UPDATE, or DELETE query with multiple parameters (Prevent SQL Injection)
    public static int executePreparedUpdate(String query, Object... params) {
        try (Connection conn = getConnection();
             PreparedStatement statement = conn.prepareStatement(query)) {

            // Set all parameters dynamically
            for (int i = 0; i < params.length; i++) {
                statement.setObject(i + 1, params[i]);
            }
            return statement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Prepared update execution failed: " + e.getMessage());
            e.printStackTrace();
        }
        return 0;
    }

    public static void closeResultSet(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
