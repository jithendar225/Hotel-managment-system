package hotel;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import net.proteanit.sql.DbUtils;
import java.sql.*;
import java.awt.event.*;

public class Room extends JFrame {

    private JPanel contentPane;
    private JTable table;
    private JLabel lblAvailability;
    private JLabel lblCleanStatus;
    private JLabel lblNewLabel;
    private JLabel lblNewLabel_1;
    private JLabel lblRoomNumber;
    private JLabel lblId;

    public Room() throws SQLException {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(450, 200, 1100, 600);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/eight.jpg"));
        Image i3 = i1.getImage().getScaledInstance(600, 600, Image.SCALE_DEFAULT);
        ImageIcon i2 = new ImageIcon(i3);
        JLabel l1 = new JLabel(i2);
        l1.setBounds(500, 0, 600, 600);
        add(l1);

        table = new JTable();
        table.setBounds(0, 40, 500, 400);
        add(table);

        JButton btnLoadData = new JButton("Load Data");
        btnLoadData.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    conn c = new conn();
                    String query = "SELECT * FROM room";
                    ResultSet rs = c.s.executeQuery(query);

                    if (rs != null && rs.next()) {
                        rs.beforeFirst();
                        table.setModel(DbUtils.resultSetToTableModel(rs));
                    } else {
                        JOptionPane.showMessageDialog(null, "No data found in the database.");
                        table.setModel(new DefaultTableModel());
                    }

                } catch (SQLException e1) {
                    e1.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error loading data from database.");
                }
            }
        });
        btnLoadData.setBounds(100, 470, 120, 30);
        btnLoadData.setBackground(Color.BLACK);
        btnLoadData.setForeground(Color.white);
        contentPane.add(btnLoadData);

        JButton btnBack = new JButton("Back");
        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Reception().setVisible(true);
                setVisible(false);
            }
        });
        btnBack.setBounds(290, 470, 120, 30);
        btnBack.setBackground(Color.BLACK);
        btnBack.setForeground(Color.white);
        contentPane.add(btnBack);

        lblId = new JLabel("Room Number");
        lblId.setBounds(12, 15, 90, 14);
        contentPane.add(lblId);

        lblAvailability = new JLabel("Availability");
        lblAvailability.setBounds(119, 15, 69, 14);
        contentPane.add(lblAvailability);

        lblCleanStatus = new JLabel("Clean Status");
        lblCleanStatus.setBounds(216, 15, 76, 14);
        contentPane.add(lblCleanStatus);

        lblNewLabel = new JLabel("Price");
        lblNewLabel.setBounds(330, 15, 46, 14);
        contentPane.add(lblNewLabel);

        lblNewLabel_1 = new JLabel("Bed Type");
        lblNewLabel_1.setBounds(417, 15, 76, 14);
        contentPane.add(lblNewLabel_1);

        getContentPane().setBackground(Color.WHITE);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Room frame = new Room();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
