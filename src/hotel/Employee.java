package hotel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.table.DefaultTableModel;
import net.proteanit.sql.DbUtils;

public class Employee extends JFrame implements ActionListener {

    JTable table;
    JButton load, back;

    Employee() {
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel l1 = new JLabel("Name");
        l1.setBounds(40, 10, 100, 20);
        add(l1);

        JLabel l2 = new JLabel("Age");
        l2.setBounds(170, 10, 100, 20);
        add(l2);

        JLabel l3 = new JLabel("Gender");
        l3.setBounds(290, 10, 100, 20);
        add(l3);

        JLabel l4 = new JLabel("Job");
        l4.setBounds(400, 10, 100, 20);
        add(l4);

        JLabel l5 = new JLabel("Salary");
        l5.setBounds(540, 10, 100, 20);
        add(l5);

        JLabel l6 = new JLabel("Phone");
        l6.setBounds(670, 10, 100, 20);
        add(l6);

        JLabel l7 = new JLabel("Aadhar");
        l7.setBounds(790, 10, 100, 20);
        add(l7);

        JLabel l8 = new JLabel("Email");
        l8.setBounds(910, 10, 100, 20);
        add(l8);

        table = new JTable();
        table.setBounds(0, 40, 1000, 400);
        add(table);

        load = new JButton("Load Data");
        load.setBounds(350, 500, 120, 30);
        load.addActionListener(this);
        add(load);

        back = new JButton("Back");
        back.setBounds(530, 500, 120, 30);
        back.addActionListener(this);
        add(back);

        setBounds(200, 100, 1000, 600);
        setVisible(true);
    }

    private void loadEmployeeData() {
        try {
            conn c = new conn();
            String query = "SELECT name, age, gender, job, salary, phone, aadhar, email FROM employee";
            ResultSet rs = c.s.executeQuery(query);

            if (rs.next()) {
                // Move cursor back to before first row
                rs.beforeFirst();
                table.setModel(DbUtils.resultSetToTableModel(rs));
            } else {
                JOptionPane.showMessageDialog(this, "No Employee Data Found");
                table.setModel(new DefaultTableModel());
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading employee data: " + e.getMessage());
        }
    }


    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == load) {
            loadEmployeeData();
        } else if (ae.getSource() == back) {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new Employee();
    }
}
