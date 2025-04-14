package view;

import controller.ATMController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginScreen extends JFrame {
    private JTextField accountField;
    private JPasswordField pinField;
    private JButton loginButton;
    private ATMController controller;

    public LoginScreen(ATMController controller) {
        this.controller = controller;

        setTitle("ATM Login");
        setSize(350, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // center screen

        // Layout setup
        setLayout(new GridLayout(3, 2, 10, 10));

        // Components
        add(new JLabel("Account Number:"));
        accountField = new JTextField();
        add(accountField);

        add(new JLabel("PIN:"));
        pinField = new JPasswordField();
        add(pinField);

        loginButton = new JButton("Login");
        add(loginButton);

        // Dummy label for spacing
        add(new JLabel(""));

        // Button logic
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String accNum = accountField.getText();
                String pin = new String(pinField.getPassword());

                if (controller.login(accNum, pin)) {
                    JOptionPane.showMessageDialog(null, "Login Successful! ✅");
                    dispose(); // close login screen
                    // 👉 Load next screen (e.g., MainMenuScreen)
                    new MainMenuScreen(controller).setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid credentials ❌", "Login Failed", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}
