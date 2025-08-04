package hotel;

import java.awt.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.*;

public class UpdateRoom extends JFrame {

    Connection conn = null;
    PreparedStatement pst = null;

    private JPanel contentPane;
    private JTextField txt_CustomerID;
    Choice c1;  // Room Number from customer table
    Choice c2;  // Availability Choice
    Choice c3;  // Clean Status Choice

    public UpdateRoom() throws SQLException {

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(530, 200, 1000, 450);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/seventh.jpg"));
        Image i3 = i1.getImage().getScaledInstance(550, 250, Image.SCALE_DEFAULT);
        ImageIcon i2 = new ImageIcon(i3);
        JLabel l1 = new JLabel(i2);
        l1.setBounds(400, 80, 600, 250);
        add(l1);

        JLabel lblUpdateRoomStatus = new JLabel("Update Room Status");
        lblUpdateRoomStatus.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblUpdateRoomStatus.setBounds(85, 11, 206, 34);
        contentPane.add(lblUpdateRoomStatus);

        JLabel lblRoomNumber = new JLabel("Room Number:");
        lblRoomNumber.setBounds(27, 87, 100, 14);
        contentPane.add(lblRoomNumber);

        c1 = new Choice();
        try {
            conn c = new conn();
            ResultSet rs = c.s.executeQuery("select * from customer");
            while (rs.next()) {
                c1.add(rs.getString("room_number"));
            }
        } catch (Exception e) {
        }
        c1.setBounds(160, 84, 140, 20);
        contentPane.add(c1);

        JLabel lblCustomerID = new JLabel("Customer ID:");
        lblCustomerID.setBounds(27, 133, 100, 14);
        contentPane.add(lblCustomerID);

        txt_CustomerID = new JTextField();
        txt_CustomerID.setBounds(160, 130, 140, 20);
        contentPane.add(txt_CustomerID);
        txt_CustomerID.setColumns(10);

        JLabel lblAvailability = new JLabel("Availability:");
        lblAvailability.setBounds(27, 187, 90, 14);
        contentPane.add(lblAvailability);

        c2 = new Choice();
        c2.add("Available");
        c2.add("Occupied");
        c2.setBounds(160, 184, 140, 20);
        contentPane.add(c2);

        JLabel lblCleanStatus = new JLabel("Clean Status:");
        lblCleanStatus.setBounds(27, 240, 90, 14);
        contentPane.add(lblCleanStatus);

        c3 = new Choice();
        c3.add("Clean");
        c3.add("Dirty");
        c3.setBounds(160, 237, 140, 20);
        contentPane.add(c3);

        JButton b1 = new JButton("Check");
        b1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String roomNumber = c1.getSelectedItem();
                    conn c = new conn();

                    ResultSet rs1 = c.s.executeQuery("select * from customer where room_number = '" + roomNumber + "'");
                    while (rs1.next()) {
                        txt_CustomerID.setText(rs1.getString("number"));
                    }

                    ResultSet rs2 = c.s.executeQuery("select * from room where room_number = '" + roomNumber + "'");
                    while (rs2.next()) {
                        c2.select(rs2.getString("Avalibility"));
                        c3.select(rs2.getString("Clean status"));
                    }

                } catch (Exception ee) {
                    ee.printStackTrace();
                }
            }
        });
        b1.setBounds(120, 315, 89, 23);
        b1.setBackground(Color.BLACK);
        b1.setForeground(Color.white);
        contentPane.add(b1);

        JButton btnUpdate = new JButton("Update");
        btnUpdate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                try {
                    String roomNumber = c1.getSelectedItem();
                    String availability = c2.getSelectedItem();
                    String cleanStatus = c3.getSelectedItem();

                    conn c = new conn();
                    String str = "update room set Avalibility = '" + availability + "', `Clean status` = '" + cleanStatus + "' where room_number = '" + roomNumber + "'";
                    c.s.executeUpdate(str);

                    JOptionPane.showMessageDialog(null, "Update Successful");

                    new Reception().setVisible(true);
                    setVisible(false);

                } catch (Exception ee) {
                    ee.printStackTrace();
                }
            }
        });
        btnUpdate.setBounds(60, 355, 89, 23);
        btnUpdate.setBackground(Color.BLACK);
        btnUpdate.setForeground(Color.white);
        contentPane.add(btnUpdate);

        JButton btnExit = new JButton("Back");
        btnExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Reception().setVisible(true);
                setVisible(false);
            }
        });
        btnExit.setBounds(180, 355, 89, 23);
        btnExit.setBackground(Color.BLACK);
        btnExit.setForeground(Color.white);
        contentPane.add(btnExit);

        getContentPane().setBackground(Color.WHITE);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    UpdateRoom frame = new UpdateRoom();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
