package hotel;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class AddEmployee extends JFrame {

    JTextField textField, textField_1, textField_3, textField_4, textField_5, textField_6;
    JComboBox<String> c1;
    JButton save;
    JRadioButton male, female;

    public AddEmployee() {
        getContentPane().setBackground(Color.WHITE);
        setTitle("ADD EMPLOYEE DETAILS");
        setLayout(null);

        JLabel name = new JLabel("NAME");
        name.setBounds(60, 30, 150, 27);
        add(name);

        textField = new JTextField();
        textField.setBounds(200, 30, 150, 27);
        add(textField);

        JLabel age = new JLabel("AGE");
        age.setBounds(60, 80, 150, 27);
        add(age);

        textField_1 = new JTextField();
        textField_1.setBounds(200, 80, 150, 27);
        add(textField_1);

        JLabel gender = new JLabel("GENDER");
        gender.setBounds(60, 120, 150, 27);
        add(gender);

        male = new JRadioButton("MALE");
        male.setBackground(Color.WHITE);
        male.setBounds(200, 120, 70, 27);
        add(male);

        female = new JRadioButton("FEMALE");
        female.setBackground(Color.WHITE);
        female.setBounds(280, 120, 70, 27);
        add(female);

        ButtonGroup bg = new ButtonGroup();
        bg.add(male);
        bg.add(female);

        JLabel job = new JLabel("JOB");
        job.setBounds(60, 170, 150, 27);
        add(job);

        String jobs[] = {"Front Desk Clerks", "Porters", "Housekeeping", "Kitchen Staff", "Room Service", "Waiter/Waitress", "Manager", "Accountant", "Chef"};
        c1 = new JComboBox<>(jobs);
        c1.setBounds(200, 170, 150, 30);
        add(c1);

        JLabel salary = new JLabel("SALARY");
        salary.setBounds(60, 220, 150, 27);
        add(salary);

        textField_3 = new JTextField();
        textField_3.setBounds(200, 220, 150, 27);
        textField_3.setEditable(false);
        add(textField_3);

        JLabel phone = new JLabel("PHONE");
        phone.setBounds(60, 270, 150, 27);
        add(phone);

        textField_4 = new JTextField();
        textField_4.setBounds(200, 270, 150, 27);
        add(textField_4);

        JLabel aadhar = new JLabel("AADHAR");
        aadhar.setBounds(60, 320, 150, 27);
        add(aadhar);

        textField_5 = new JTextField();
        textField_5.setBounds(200, 320, 150, 27);
        add(textField_5);

        JLabel email = new JLabel("EMAIL");
        email.setBounds(60, 370, 150, 27);
        add(email);

        textField_6 = new JTextField();
        textField_6.setBounds(200, 370, 150, 27);
        add(textField_6);

        save = new JButton("SAVE");
        save.setBounds(200, 420, 150, 30);
        save.setBackground(Color.BLACK);
        save.setForeground(Color.WHITE);
        add(save);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/tenth.jpg"));
        Image i2 = i1.getImage().getScaledInstance(500, 500, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(400, 80, 500, 400);
        add(image);

        c1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String job = (String) c1.getSelectedItem();
                switch (job) {
                    case "Front Desk Clerks": textField_3.setText("15000"); break;
                    case "Porters": textField_3.setText("12000"); break;
                    case "Housekeeping": textField_3.setText("10000"); break;
                    case "Kitchen Staff": textField_3.setText("13000"); break;
                    case "Room Service": textField_3.setText("11000"); break;
                    case "Waiter/Waitress": textField_3.setText("14000"); break;
                    case "Manager": textField_3.setText("30000"); break;
                    case "Accountant": textField_3.setText("25000"); break;
                    case "Chef": textField_3.setText("20000"); break;
                }
            }
        });

        save.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                String name = textField.getText();
                String age = textField_1.getText();
                String salary = textField_3.getText();
                String phone = textField_4.getText();
                String aadhar = textField_5.getText();
                String email = textField_6.getText();
                String gender = null;
                if (male.isSelected()) gender = "male";
                else if (female.isSelected()) gender = "female";
                String job = (String) c1.getSelectedItem();

                if (!phone.matches("\\d{10}")) {
                    JOptionPane.showMessageDialog(null, "Invalid Phone Number (Must be 10 digits)");
                    return;
                }
                if (!aadhar.matches("\\d{12}")) {
                    JOptionPane.showMessageDialog(null, "Invalid Aadhar Number (Must be 12 digits)");
                    return;
                }

                try {
                    conn c = new conn();
                    String str = "INSERT INTO employee values('" + name + "', '" + age + "', '" + gender + "','" + job + "', '" + salary + "', '" + phone + "','" + aadhar + "', '" + email + "')";
                    c.s.executeUpdate(str);
                    JOptionPane.showMessageDialog(null, "Employee Added Successfully");
                    setVisible(false);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        setSize(950, 600);
        setLocation(500, 200);
        setVisible(true);
    }

    public static void main(String[] args) {
        new AddEmployee();
    }
}
