package hotel;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.sql.*;
import net.proteanit.sql.DbUtils;

public class ManagerInfo extends JFrame {
    private JPanel contentPane;
    private JTable table;
    private JButton btnLoadData, btnExit;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ManagerInfo frame = new ManagerInfo();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    // Constructor
    public ManagerInfo() throws SQLException {
        setTitle("Manager Information");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(430, 200, 1000, 600);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        table = new JTable();
        table.setBounds(0, 40, 1000, 450);
        contentPane.add(table);
        
        btnLoadData = new JButton("Load Data");
        btnLoadData.setBounds(350, 500, 120, 30);
        btnLoadData.setBackground(Color.BLACK);
        btnLoadData.setForeground(Color.WHITE);
        contentPane.add(btnLoadData);

        btnExit = new JButton("Back");
        btnExit.setBounds(510, 500, 120, 30);
        btnExit.setBackground(Color.BLACK);
        btnExit.setForeground(Color.WHITE);
        contentPane.add(btnExit);

        // Labels for column headers
        String[] labels = {"Name", "Age", "Gender", "Job", "Salary", "Phone", "Aadhar", "Gmail"};
        int x = 41;
        for (String label : labels) {
            JLabel lbl = new JLabel(label);
            lbl.setBounds(x, 10, 86, 14);
            contentPane.add(lbl);
            x += 120;
        }

        getContentPane().setBackground(Color.WHITE);

        // Action Listener to Load Data
        btnLoadData.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    conn c = new conn();
                    String query = "SELECT * FROM Employee WHERE job = 'Manager'";
                    ResultSet rs = c.s.executeQuery(query);
                    table.setModel(DbUtils.resultSetToTableModel(rs)); 
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });

        // Action Listener to Exit
        btnExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);  // Close this window
                new Reception().setVisible(true);  // Assuming Reception.java exists
            }
        });
    }
}
