package hotel;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.sql.*;

public class NewCustomer extends JFrame {
    Connection conn = null;
    PreparedStatement pst = null;
    private JPanel contentPane;
    private JTextField t1, t2, t3, t5, t6, t4; // Added t4 for Check-In Time
    JComboBox comboBox;
    JRadioButton r1, r2;
    Choice c1;
    JComboBox<String> checkInStatusComboBox;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    NewCustomer frame = new NewCustomer();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public NewCustomer() throws SQLException {
        setBounds(530, 200, 850, 550);
        contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.setLayout(null);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/fifth.png"));
        Image i3 = i1.getImage().getScaledInstance(300, 400, Image.SCALE_DEFAULT);
        ImageIcon i2 = new ImageIcon(i3);
        JLabel l1 = new JLabel(i2);
        l1.setBounds(480, 10, 300, 500);
        add(l1);

        JLabel lblName = new JLabel("NEW CUSTOMER FORM");
        lblName.setFont(new Font("Yu Mincho", Font.PLAIN, 20));
        lblName.setBounds(118, 11, 260, 53);
        contentPane.add(lblName);

        JLabel lblId = new JLabel("ID :");
        lblId.setBounds(35, 76, 200, 14);
        contentPane.add(lblId);

        comboBox = new JComboBox(new String[] { "Passport", "Aadhar Card", "Voter Id", "Driving license" });
        comboBox.setBounds(271, 73, 150, 20);
        contentPane.add(comboBox);

        JLabel l2 = new JLabel("Number :");
        l2.setBounds(35, 111, 200, 14);
        contentPane.add(l2);

        t1 = new JTextField();
        t1.setBounds(271, 111, 150, 20);
        contentPane.add(t1);
        t1.setColumns(10);

        JLabel lblName_1 = new JLabel("Name :");
        lblName_1.setBounds(35, 151, 200, 14);
        contentPane.add(lblName_1);

        t2 = new JTextField();
        t2.setBounds(271, 151, 150, 20);
        contentPane.add(t2);
        t2.setColumns(10);

        JLabel lblGender = new JLabel("Gender :");
        lblGender.setBounds(35, 191, 200, 14);
        contentPane.add(lblGender);

        r1 = new JRadioButton("Male");
        r1.setFont(new Font("Raleway", Font.BOLD, 14));
        r1.setBackground(Color.WHITE);
        r1.setBounds(271, 191, 80, 12);
        add(r1);

        r2 = new JRadioButton("Female");
        r2.setFont(new Font("Raleway", Font.BOLD, 14));
        r2.setBackground(Color.WHITE);
        r2.setBounds(350, 191, 100, 12);
        add(r2);

        JLabel lblCountry = new JLabel("Country :");
        lblCountry.setBounds(35, 231, 200, 14);
        contentPane.add(lblCountry);

        JLabel lblReserveRoomNumber = new JLabel("Allocated Room Number :");
        lblReserveRoomNumber.setBounds(35, 274, 200, 14);
        contentPane.add(lblReserveRoomNumber);

        c1 = new Choice();
        try {
            conn c = new conn();
            ResultSet rs = c.s.executeQuery("select * from room");
            while (rs.next()) {
                c1.add(rs.getString("room_number"));
            }
        } catch (Exception e) {
        }
        c1.setBounds(271, 274, 150, 20);
        contentPane.add(c1);

        JLabel lblCheckInStatus = new JLabel("Check-In Status :");
        lblCheckInStatus.setBounds(35, 316, 200, 14);
        contentPane.add(lblCheckInStatus);

        String[] checkInStatusOptions = { "Not Checked-In", "Checked-In", "Checked-Out" };
        checkInStatusComboBox = new JComboBox<>(checkInStatusOptions);
        checkInStatusComboBox.setBounds(271, 316, 150, 20);
        contentPane.add(checkInStatusComboBox);

        JLabel lblDeposite = new JLabel("Deposit :");
        lblDeposite.setBounds(35, 359, 200, 14);
        contentPane.add(lblDeposite);

        t3 = new JTextField();
        t3.setBounds(271, 231, 150, 20);
        contentPane.add(t3);
        t3.setColumns(10);

        t5 = new JTextField();
        t5.setBounds(271, 316, 150, 20);
        contentPane.add(t5);
        t5.setColumns(10);

        t6 = new JTextField();
        t6.setBounds(271, 359, 150, 20);
        contentPane.add(t6);
        t6.setColumns(10);

        // Add Check-In Time Label and Field
        JLabel lblCheckInTime = new JLabel("Check-In Time:");
        lblCheckInTime.setBounds(35, 400, 200, 14);
        contentPane.add(lblCheckInTime);

        t4 = new JTextField();
        t4.setBounds(271, 400, 150, 20);
        t4.setEditable(false);  // User should not modify it
        contentPane.add(t4);
        t4.setColumns(10);

        // Get current date and time
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        String currentDateTime = formatter.format(date);
        t4.setText(currentDateTime);  // Displaying current time in the field

        JButton btnNewButton = new JButton("Add");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                conn c = new conn();
                String radio = null;

                if (r1.isSelected()) {
                    radio = "Male";
                } else if (r2.isSelected()) {
                    radio = "Female";
                }

                String s6 = c1.getSelectedItem();
                String s7 = (String) checkInStatusComboBox.getSelectedItem();
                String checkInTime = t4.getText(); // Use the displayed check-in time

                try {
                    String s1 = (String) comboBox.getSelectedItem();
                    String s2 = t1.getText();
                    String s3 = t2.getText();
                    String s4 = radio;
                    String s5 = t3.getText();
                    String s8 = t5.getText();
                    String s10 = t6.getText();

                    String q1 = "INSERT INTO customer (id_type, number, name, gender, country, room_number, check_in_status, deposit, check_in_time) "
                            + "VALUES ('" + s1 + "','" + s2 + "','" + s3 + "','" + s4 + "','" + s5 + "','" + s6 + "','" + s7 + "','" + s8 + "','" + checkInTime + "')";
                    c.s.executeUpdate(q1);

                    JOptionPane.showMessageDialog(null, "Data Inserted Successfully");
                    new Reception().setVisible(true);
                    setVisible(false);
                } catch (SQLException e1) {
                    System.out.println(e1.getMessage());
                } catch (NumberFormatException s) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid Number");
                }
            }
        });
        btnNewButton.setBounds(100, 480, 120, 30);
        btnNewButton.setBackground(Color.BLACK);
        btnNewButton.setForeground(Color.WHITE);
        contentPane.add(btnNewButton);

        JButton btnExit = new JButton("Back");
        btnExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Reception().setVisible(true);
                setVisible(false);
            }
        });
        btnExit.setBounds(260, 480, 120, 30);
        btnExit.setBackground(Color.BLACK);
        btnExit.setForeground(Color.WHITE);
        contentPane.add(btnExit);

        getContentPane().setBackground(Color.WHITE);
    }
}
