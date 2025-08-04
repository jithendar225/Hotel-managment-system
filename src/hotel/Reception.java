package hotel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Reception extends JFrame {

    private static final long serialVersionUID = 1L;

    public static void main(String[] args) {
        new Reception();
    }

    public Reception() {
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Header Panel
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(70, 130, 180));
        JLabel headerLabel = new JLabel("Hotel Reception");
        headerLabel.setFont(new Font("Arial", Font.BOLD, 28));
        headerLabel.setForeground(Color.WHITE);
        headerPanel.add(headerLabel);
        add(headerPanel, BorderLayout.NORTH);

        // Left Sidebar Panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(12, 1, 10, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        buttonPanel.setPreferredSize(new Dimension(300, getHeight()));

        Color lightBlue = new Color(100, 149, 237);
        Color hoverColor = new Color(70, 130, 180);
        String[] buttonLabels = {
            "New Customer Form", "Room", "Add", "All Employee Info", "Customer Info",
            "Manager Info", "Check Out", "Update Check Status", "Update Room Status",
            "Pick up Service", "Search Room", "Log Out"
        };
        Class<?>[] actions = {
            NewCustomer.class, Room.class, Add.class, Employee.class, CustomerInfo.class,
            ManagerInfo.class, CheckOut.class, UpdateCheck.class, UpdateRoom.class,
            PickUp.class, SearchRoom.class, Login.class
        };

        for (int i = 0; i < buttonLabels.length; i++) {
            JButton button = new JButton(buttonLabels[i]);
            button.setBackground(lightBlue);
            button.setForeground(Color.WHITE);
            button.setFont(new Font("Arial", Font.BOLD, 16));
            button.setFocusPainted(false);
            button.setPreferredSize(new Dimension(250, 50));
            button.setCursor(new Cursor(Cursor.HAND_CURSOR));
            button.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2, true));

            button.addMouseListener(new MouseAdapter() {
                public void mouseEntered(MouseEvent evt) {
                    button.setBackground(hoverColor);
                }
                public void mouseExited(MouseEvent evt) {
                    button.setBackground(lightBlue);
                }
            });

            final int index = i;
            button.addActionListener(e -> {
                try {
                    JFrame frame = (JFrame) actions[index].getDeclaredConstructor().newInstance();
                    frame.setVisible(true);
                    setVisible(false);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            });
            buttonPanel.add(button);
        }
        add(buttonPanel, BorderLayout.WEST);

        // Image Panel (Right Side)
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/reception.jpg"));
        Image i2 = i1.getImage().getScaledInstance(1180, 1190, Image.SCALE_SMOOTH);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel imageLabel = new JLabel(i3);
        imageLabel.setHorizontalAlignment(JLabel.CENTER);
        imageLabel.setVerticalAlignment(JLabel.CENTER);
        add(imageLabel, BorderLayout.CENTER);

        // Footer Panel
        JPanel footerPanel = new JPanel();
        footerPanel.setBackground(new Color(70, 130, 180));
        JLabel footerLabel = new JLabel("\u00A9 2025 Hotel Management System | All Rights Reserved");
        footerLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        footerLabel.setForeground(Color.WHITE);
        footerPanel.add(footerLabel);
        add(footerPanel, BorderLayout.SOUTH);

        getContentPane().setBackground(Color.WHITE);
        setVisible(true);
    }
}
