package hotel;

import java.sql.*;

public class conn {
    Connection c;
    Statement s;
	protected Object conn;

    public conn() {
        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish the database connection
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotel", "root", "Jithendar225#");

            // Create a statement object for executing SQL queries
            s = c.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY); 
            
            System.out.println("Database Connected Successfully!");

        } catch (ClassNotFoundException e) {
            System.out.println("MySQL JDBC Driver not found. Add the connector JAR to the classpath.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Database connection error! Check your credentials and database status.");
            e.printStackTrace();
        }
    }
}
