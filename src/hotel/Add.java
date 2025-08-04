package hotel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Add extends JFrame {

    public Add() {
        setBounds(530, 200, 400, 300);
        JPanel contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JButton btnAddEmployee = new JButton("Add Employee");
        btnAddEmployee.setBounds(100, 30, 200, 30);
        btnAddEmployee.addActionListener(e -> new AddEmployee().setVisible(true));
        contentPane.add(btnAddEmployee);

        JButton btnAddRoom = new JButton("Add Room");
        btnAddRoom.setBounds(100, 80, 200, 30);
        btnAddRoom.addActionListener(e -> new AddRoom().setVisible(true));
        contentPane.add(btnAddRoom);

        JButton btnAddDrivers = new JButton("Add Drivers");
        btnAddDrivers.setBounds(100, 130, 200, 30);
        btnAddDrivers.addActionListener(e -> new AddDrivers().setVisible(true));
        contentPane.add(btnAddDrivers);

        JButton btnBack = new JButton("Back");
        btnBack.setBounds(100, 180, 200, 30);
        btnBack.addActionListener(e -> {
            new Reception().setVisible(true);
            setVisible(false);
        });
        contentPane.add(btnBack);

        setVisible(true);
    }

    public static void main(String[] args) {
        new Add();
    }
}