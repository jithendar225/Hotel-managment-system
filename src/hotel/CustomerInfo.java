package hotel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;

public class CustomerInfo {
    private JTable customerTable;  // JTable to display customer information
    private Connection con;

    public CustomerInfo() {
        // Initialize JTable and database connection
        customerTable = new JTable();
        con = null;

        // Example method to load customer data into JTable
        loadCustomerData();
    }

    private void loadCustomerData() {
        // Assuming you have a connection to your MySQL database
        String url = "jdbc:mysql://localhost:3306/hotel";  // Your database URL
        String user = "root";  // Your database username
        String password = "Jithendar225#";  // Your database password

        // SQL query to fetch customer details
        String query = "SELECT id_type, number, name, gender, country, room_number, check_in_status, deposit, check_in_time ";  // Modify based on your table structure

        try {
            // Establish the connection to the database
            con = DriverManager.getConnection(url, user, password);
            PreparedStatement pst = con.prepareStatement(query);
            ResultSet rs = pst.executeQuery();

            // Create a table model with column names
            DefaultTableModel model = new DefaultTableModel(
                new String[]{"ID", "Type", "Number", "Name", "Gender", "Country", "Room Number", "Check-In Status", "Deposit", "Check-In Time"}, 0);

            // Add rows from the ResultSet into the table model
            while (rs.next()) {
                model.addRow(new Object[]{
                        rs.getInt("id"),
                        rs.getString("type"),
                        rs.getString("number"),
                        rs.getString("name"),
                        rs.getString("gender"),
                        rs.getString("country"),
                        rs.getInt("room_number"),
                        rs.getString("check_in_status"),
                        rs.getDouble("deposit"),
                        rs.getTimestamp("check_in_time")
                });
            }

            // Set the table model to the JTable
            customerTable.setModel(model);

            // Close the resources
            rs.close();
            pst.close();
            con.close();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error loading customer data: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new CustomerInfo();  // Start the CustomerInfo frame
            }
        });
    }
}
