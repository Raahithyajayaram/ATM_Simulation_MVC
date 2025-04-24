package view;

import controller.UserController;
import model.User;

import javax.swing.*;
import java.awt.*;

public class UserDashboardFrame extends JFrame {
    private JButton btnBalance, btnDeposit, btnWithdraw;
    private User user;
    private UserController userController;

    public UserDashboardFrame(User user) {
        this.user = user;
        this.userController = new UserController();

        setTitle("User Dashboard - Welcome, " + user.getUsername());
        setExtendedState(JFrame.MAXIMIZED_BOTH); 
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBackground(new Color(40, 44, 52));

        btnBalance = new JButton("Check Balance");
        btnDeposit = new JButton("Deposit");
        btnWithdraw = new JButton("Withdraw");

        Font buttonFont = new Font("Arial", Font.BOLD, 22);
        btnBalance.setFont(buttonFont);
        btnDeposit.setFont(buttonFont);
        btnWithdraw.setFont(buttonFont);

        btnBalance.setBackground(new Color(0, 153, 204));
        btnDeposit.setBackground(new Color(0, 204, 102));
        btnWithdraw.setBackground(new Color(255, 102, 102));

        btnBalance.setForeground(Color.WHITE);
        btnDeposit.setForeground(Color.WHITE);
        btnWithdraw.setForeground(Color.WHITE);

        btnBalance.setFocusPainted(false);
        btnDeposit.setFocusPainted(false);
        btnWithdraw.setFocusPainted(false);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 20, 20, 20);

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(btnBalance, gbc);

        gbc.gridy = 1;
        panel.add(btnDeposit, gbc);

        gbc.gridy = 2;
        panel.add(btnWithdraw, gbc);

        add(panel);

        btnBalance.addActionListener(e -> {
            double balance = userController.getBalance(user.getId());
            JOptionPane.showMessageDialog(this, "Current Balance: ₹" + balance);
        });

        btnDeposit.addActionListener(e -> {
            String amountStr = JOptionPane.showInputDialog("Enter amount to deposit:");
            try {
                double amount = Double.parseDouble(amountStr);
                userController.deposit(user.getId(), amount);
                JOptionPane.showMessageDialog(this, "Deposited ₹" + amount);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Invalid amount.");
            }
        });

        btnWithdraw.addActionListener(e -> {
            String amountStr = JOptionPane.showInputDialog("Enter amount to withdraw:");
            try {
                double amount = Double.parseDouble(amountStr);
                if (userController.withdraw(user.getId(), amount)) {
                    JOptionPane.showMessageDialog(this, "Withdrawn ₹" + amount);
                } else {
                    JOptionPane.showMessageDialog(this, "Insufficient balance.");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Invalid amount.");
            }
        });
    }
}