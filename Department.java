import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DepartmentDatabase {
    // JDBC URL, username, and password of MySQL server
    private static final String URL = "jdbc:mysql://localhost:3306/departments";
    private static final String USER = "your_username";
    private static final String PASSWORD = "your_password";

    // JDBC variables for opening, closing, and managing connection
    private static Connection connection;
    private static Statement statement;

    public static void main(String[] args) {
        try {
            // Connect to MySQL database
            connection = DriverManager.getConnection(URL, USER, PASSWORD);

            // Create a statement
            statement = connection.createStatement();

            // Create table if not exists
            createTable();

            // Insert department
            Department department = new Department(1, "IT");
            insertDepartment(department);

            // Close resources
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Create department table
    private static void createTable() throws SQLException {
        String createTableQuery = "CREATE TABLE IF NOT EXISTS department (id INT PRIMARY KEY, name VARCHAR(255))";
        statement.executeUpdate(createTableQuery);
    }

    // Insert department into the database
    private static void insertDepartment(Department department) throws SQLException {
        String insertQuery = String.format("INSERT INTO department VALUES (%d, '%s')",
                department.getId(), department.getName());
        statement.executeUpdate(insertQuery);
        System.out.println("Department inserted successfully.");
    }

    // Department class representing Department object
    static class Department {
        private int id;
        private String name;

        public Department(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }
    }
}