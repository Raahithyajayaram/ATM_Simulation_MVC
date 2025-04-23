package view;

import javax.swing.*;
import controller.AdminController;
import model.User;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddUserDialog extends JDialog {
    private JTextField txtUsername, txtPin, txtBalance;
    private JButton btnAdd;

    private AdminController adminController;

    public AddUserDialog(JFrame parent) {
        super(parent, "Add User", true);
        setSize(300, 250);
        setLocationRelativeTo(parent);

        adminController = new AdminController();

        // Create UI components
        JLabel lblUsername = new JLabel("Username:");
        JLabel lblPin = new JLabel("PIN:");
        JLabel lblBalance = new JLabel("Balance:");

        txtUsername = new JTextField(15);
        txtPin = new JTextField(15);
        txtBalance = new JTextField(15);

        btnAdd = new JButton("Add User");

        // Panel to hold components
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2));

        panel.add(lblUsername);
        panel.add(txtUsername);
        panel.add(lblPin);
        panel.add(txtPin);
        panel.add(lblBalance);
        panel.add(txtBalance);
        panel.add(new JLabel());
        panel.add(btnAdd);

        // Add action listener for Add button
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = txtUsername.getText();
                String pin = txtPin.getText();
                String balanceStr = txtBalance.getText();
                try {
                    double balance = Double.parseDouble(balanceStr);
                    User user = new User(username, pin, balance);
                    if (adminController.addUser(user)) {
                        JOptionPane.showMessageDialog(AddUserDialog.this, "User added successfully!");
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(AddUserDialog.this, "Error adding user.");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(AddUserDialog.this, "Invalid balance.");
                }
            }
        });

        add(panel);
    }
}
