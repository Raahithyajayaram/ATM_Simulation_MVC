package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controller.ATMController;

public class LoginScreen extends JFrame {
    private final ATMController controller;
    private JTextField accountNumberField;
    private JPasswordField pinField;

    public LoginScreen(ATMController controller) {
        this.controller = controller;
        setTitle("ATM Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        initUI();
        setResizable(true);
        setLocationRelativeTo(null);
    }

    private void initUI() {
        setTitle("ATM Login");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400, 250);
        setResizable(false);
        setLocationRelativeTo(null); // center

        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        mainPanel.setBackground(new Color(240, 248, 255)); // light blue

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;

        // Title
        JLabel titleLabel = new JLabel("Welcome to Secure ATM");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        titleLabel.setForeground(new Color(25, 25, 112));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        mainPanel.add(titleLabel, gbc);

        // Reset for fields
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;

        // Account Number Label
        JLabel accountLabel = new JLabel("Account Number:");
        accountLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 1;
        mainPanel.add(accountLabel, gbc);

        // Account Number Field
        accountNumberField = new JTextField(15);
        accountNumberField.setForeground(Color.BLACK);
        accountNumberField.setBackground(Color.WHITE);
        accountNumberField.setEditable(true);
        gbc.gridx = 1;
        accountNumberField.requestFocusInWindow();
        accountNumberField.setFocusable(true);
        mainPanel.add(accountNumberField, gbc);

        // PIN Label
        JLabel pinLabel = new JLabel("PIN:");
        pinLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 2;
        mainPanel.add(pinLabel, gbc);

        // PIN Field
        pinField = new JPasswordField(15);
        pinField.setForeground(Color.BLACK);
        pinField.setBackground(Color.WHITE);
        gbc.gridx = 1;
        pinField.setEchoChar('●'); 
        pinField.setFocusable(true); 
        pinField.setEditable(true);
        mainPanel.add(pinField, gbc);

        // Login Button
        JButton loginButton = new JButton("Login");
        loginButton.setBackground(new Color(100, 149, 237));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        loginButton.setFocusPainted(false);
        loginButton.setPreferredSize(new Dimension(120, 35));
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        mainPanel.add(loginButton, gbc);

        // Action Listener
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String accNum = accountNumberField.getText();
                String pin = new String(pinField.getPassword());
                System.out.println("DEBUG: accNum='" + accNum + "', pin='" + pin + "'");
                if (controller.login(accNum, pin)) {
                    JOptionPane.showMessageDialog(LoginScreen.this, "Login successful!");
                    new MainMenuScreen(controller).setVisible(true);
                    LoginScreen.this.dispose();
                } else {
                    JOptionPane.showMessageDialog(
                        LoginScreen.this,
                        "Invalid account number or PIN.",
                        "Login Failed",
                        JOptionPane.ERROR_MESSAGE
                    );
                }
            }
        });

        add(mainPanel);
        setVisible(true);
    }
}
