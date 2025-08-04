package hotel;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.sql.*;

public class UpdateCheck extends JFrame {
	Connection conn = null;
	PreparedStatement pst = null;
	private JPanel contentPane;
	private JTextField txt_Name, txt_Status, txt_Deposit, txt_Pending, txt_CheckInTime;

	Choice c1;
	int roomPrice = 0; // To store price of the room

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				UpdateCheck frame = new UpdateCheck();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	public UpdateCheck() throws SQLException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(500, 200, 950, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblTitle = new JLabel("Update Customer Check-In Details");
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTitle.setBounds(124, 11, 350, 25);
		contentPane.add(lblTitle);

		JLabel lblRoom = new JLabel("Room Number:");
		lblRoom.setBounds(25, 88, 100, 14);
		contentPane.add(lblRoom);

		c1 = new Choice();
		try {
			conn c = new conn();
			ResultSet rs = c.s.executeQuery("SELECT room_number FROM customer");
			while (rs.next()) {
				c1.add(rs.getString("room_number"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		c1.setBounds(248, 85, 140, 20);
		contentPane.add(c1);

		JLabel lblName = new JLabel("Name:");
		lblName.setBounds(25, 129, 100, 14);
		contentPane.add(lblName);

		txt_Name = new JTextField();
		txt_Name.setBounds(248, 126, 140, 20);
		contentPane.add(txt_Name);

		JLabel lblStatus = new JLabel("Check-In Status:");
		lblStatus.setBounds(25, 174, 150, 14);
		contentPane.add(lblStatus);

		txt_Status = new JTextField();
		txt_Status.setBounds(248, 171, 140, 20);
		contentPane.add(txt_Status);

		JLabel lblCheckInTime = new JLabel("Check-In Time:");
		lblCheckInTime.setBounds(25, 216, 150, 14);
		contentPane.add(lblCheckInTime);

		txt_CheckInTime = new JTextField();
		txt_CheckInTime.setBounds(248, 213, 140, 20);
		contentPane.add(txt_CheckInTime);

		JLabel lblDeposit = new JLabel("Amount Paid (Rs):");
		lblDeposit.setBounds(25, 261, 150, 14);
		contentPane.add(lblDeposit);

		txt_Deposit = new JTextField();
		txt_Deposit.setBounds(248, 258, 140, 20);
		contentPane.add(txt_Deposit);

		JLabel lblPending = new JLabel("Pending Amount (Rs):");
		lblPending.setBounds(25, 302, 150, 14);
		contentPane.add(lblPending);

		txt_Pending = new JTextField();
		txt_Pending.setBounds(248, 299, 140, 20);
		contentPane.add(txt_Pending);

		// Auto update pending amount when deposit changes
		txt_Deposit.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				autoUpdatePendingAmount();
			}
		});

		JButton btnCheck = new JButton("Check");
		btnCheck.addActionListener(e -> fetchCustomerDetails());
		btnCheck.setBounds(56, 378, 89, 23);
		btnCheck.setBackground(Color.BLACK);
		btnCheck.setForeground(Color.WHITE);
		contentPane.add(btnCheck);

		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(e -> updateCustomerDetails());
		btnUpdate.setBounds(168, 378, 89, 23);
		btnUpdate.setBackground(Color.BLACK);
		btnUpdate.setForeground(Color.WHITE);
		contentPane.add(btnUpdate);

		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(e -> {
			new Reception().setVisible(true);
			setVisible(false);
		});
		btnBack.setBounds(281, 378, 89, 23);
		btnBack.setBackground(Color.BLACK);
		btnBack.setForeground(Color.WHITE);
		contentPane.add(btnBack);

		getContentPane().setBackground(Color.WHITE);

		ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/nine.jpg"));
		JLabel l1 = new JLabel(i1);
		l1.setBounds(450, 70, 476, 270);
		add(l1);
	}

	private void fetchCustomerDetails() {
		try {
			String roomNumber = c1.getSelectedItem();
			conn c = new conn();

			ResultSet rs1 = c.s.executeQuery("SELECT * FROM customer WHERE room_number = '" + roomNumber + "'");
			if (rs1.next()) {
				txt_Name.setText(rs1.getString("name"));
				txt_Status.setText(rs1.getString("check_in_status"));
				txt_CheckInTime.setText(rs1.getString("check_in_time"));
				txt_Deposit.setText(rs1.getString("deposit"));
			}

			ResultSet rs2 = c.s.executeQuery("SELECT price FROM room WHERE room_number = '" + roomNumber + "'");
			if (rs2.next()) {
				roomPrice = Integer.parseInt(rs2.getString("price"));
			}

			autoUpdatePendingAmount();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void autoUpdatePendingAmount() {
		try {
			int deposit = 0;
			if (!txt_Deposit.getText().isEmpty()) {
				deposit = Integer.parseInt(txt_Deposit.getText());
			}
			int pending = roomPrice - deposit;
			txt_Pending.setText(String.valueOf(pending));
		} catch (Exception e) {
			txt_Pending.setText("");
		}
	}

	private void updateCustomerDetails() {
		try {
			conn c = new conn();
			String roomNumber = c1.getSelectedItem();
			String name = txt_Name.getText();
			String status = txt_Status.getText();
			String deposit = txt_Deposit.getText();

			c.s.executeUpdate("UPDATE customer SET name='" + name + "', check_in_status='" + status + "', deposit='" + deposit + "' WHERE room_number='" + roomNumber + "'");

			JOptionPane.showMessageDialog(null, "Data Updated Successfully");
			new Reception().setVisible(true);
			setVisible(false);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
