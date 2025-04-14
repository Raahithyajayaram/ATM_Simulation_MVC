package view;

import controller.ATMController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class MainMenuScreen extends JFrame {
    private ATMController controller;
    private JTextArea receiptArea;  // Add this to display the receipt

    public MainMenuScreen(ATMController controller) {
        this.controller = controller;

        // Set up JFrame properties
        setTitle("ATM - Main Menu");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create components
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JButton withdrawButton = new JButton("Withdraw");
        JButton balanceButton = new JButton("Check Balance");
        JButton miniStatementButton = new JButton("Mini Statement");
        JButton logoutButton = new JButton("Logout");

        // Add a JTextArea for the receipt
        receiptArea = new JTextArea(10, 30);
        receiptArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(receiptArea);
        panel.add(scrollPane);

        // Withdraw Button Action
        withdrawButton.addActionListener(e -> {
            String amountStr = JOptionPane.showInputDialog(this, "Enter amount to withdraw:");
            try {
                double amount = Double.parseDouble(amountStr);
                controller.withdraw(amount);
                String receipt = controller.generateReceipt(amount);  // Get the receipt
                receiptArea.setText(receipt);  // Show the receipt in the JTextArea
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid amount entered!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Balance Button Action
        balanceButton.addActionListener(e -> {
            double balance = controller.getCurrentBalance();
            JOptionPane.showMessageDialog(this, "Your balance is ₹ " + balance);
        });

        // Mini Statement Button Action
        miniStatementButton.addActionListener(e -> {
            StringBuilder statement = new StringBuilder("Last 5 transactions:\n");
            for (String txn : controller.getTransactionHistory()) {
                statement.append("• " + txn + "\n");
            }
            JOptionPane.showMessageDialog(this, statement.toString());
        });

        // Logout Button Action
        logoutButton.addActionListener(e -> {
            controller.logout();
            JOptionPane.showMessageDialog(this, "Logged out successfully.");
            dispose(); // Close the current screen
            new LoginScreen(controller).setVisible(true); // Show login screen
        });

        // Add components to panel
        panel.add(withdrawButton);
        panel.add(balanceButton);
        panel.add(miniStatementButton);
        panel.add(logoutButton);

        // Set the layout and add the panel
        add(panel);
    }
}