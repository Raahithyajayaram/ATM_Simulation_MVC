package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import controller.ATMController;

public class MainMenuScreen extends JFrame {
    private ATMController controller;
    private JTextArea receiptArea;
    private boolean isDarkMode = false;

    public MainMenuScreen(ATMController controller) {
        this.controller = controller;

        // Frame settings
        setTitle("ATM - Main Menu");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(245, 245, 245));
        getContentPane().setLayout(new BorderLayout());

        // Create theme toggle button
        JButton themeToggleButton = createStyledButton("Toggle Dark Mode");
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        topPanel.setOpaque(false);
        topPanel.add(themeToggleButton);
        add(topPanel, BorderLayout.NORTH);

        // Main vertical panel centered
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(new Color(245, 245, 245));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        mainPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Buttons
        JButton withdrawButton = createStyledButton("Withdraw");
        JButton balanceButton = createStyledButton("Check Balance");
        JButton miniStatementButton = createStyledButton("Mini Statement");
        JButton logoutButton = createStyledButton("Logout");

        // Receipt area (increased size)
        receiptArea = new JTextArea(15, 50);
        receiptArea.setEditable(false);
        receiptArea.setFont(new Font("Courier New", Font.PLAIN, 14));
        receiptArea.setBackground(new Color(255, 255, 255));
        receiptArea.setBorder(BorderFactory.createTitledBorder("Receipt"));
        receiptArea.setMargin(new Insets(10, 10, 10, 10));
        JScrollPane scrollPane = new JScrollPane(receiptArea);
        scrollPane.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Add components to mainPanel with spacing
        mainPanel.add(Box.createVerticalGlue());
        mainPanel.add(withdrawButton);
        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(balanceButton);
        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(miniStatementButton);
        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(logoutButton);
        mainPanel.add(Box.createVerticalStrut(25));
        mainPanel.add(scrollPane);
        mainPanel.add(Box.createVerticalGlue());

        add(mainPanel, BorderLayout.CENTER);

        // Action listeners
        withdrawButton.addActionListener(e -> {
            String amountStr = JOptionPane.showInputDialog(this, "Enter amount to withdraw:");
            try {
                double amount = Double.parseDouble(amountStr);
                controller.withdraw(amount);
                String receipt = controller.generateReceipt(amount);
                String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                receiptArea.setText("ATM RECEIPT\n\n" + receipt + "\nDate/Time: " + timestamp);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid amount entered!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        balanceButton.addActionListener(e -> {
            double balance = controller.getCurrentBalance();
            JOptionPane.showMessageDialog(this, "Your balance is ₹ " + balance);
        });

        miniStatementButton.addActionListener(e -> {
            StringBuilder statement = new StringBuilder("LAST 5 TRANSACTIONS\n\n");
            for (String txn : controller.getTransactionHistory()) {
                statement.append("• ").append(txn).append("\n");
            }
            JOptionPane.showMessageDialog(this, statement.toString());
        });

        logoutButton.addActionListener(e -> {
            controller.logout();
            JOptionPane.showMessageDialog(this, "Logged out successfully.");
            dispose();
            new LoginScreen(controller).setVisible(true);
        });

        themeToggleButton.addActionListener(e -> toggleTheme());

        setVisible(true);
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.BOLD, 16));
        button.setBackground(new Color(30, 144, 255));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setMaximumSize(new Dimension(260, 45));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        button.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                button.setBackground(new Color(0, 120, 215));
            }

            public void mouseExited(MouseEvent evt) {
                button.setBackground(new Color(30, 144, 255));
            }
        });

        return button;
    }

    private void toggleTheme() {
        isDarkMode = !isDarkMode;
        Color bgColor = isDarkMode ? new Color(40, 40, 40) : new Color(245, 245, 245);
        Color textColor = isDarkMode ? Color.WHITE : Color.BLACK;
        getContentPane().setBackground(bgColor);
        for (Component c : getContentPane().getComponents()) {
            c.setBackground(bgColor);
            c.setForeground(textColor);
            if (c instanceof JPanel) {
                for (Component child : ((JPanel) c).getComponents()) {
                    child.setBackground(bgColor);
                    child.setForeground(textColor);
                }
            }
        }
        receiptArea.setBackground(isDarkMode ? new Color(60, 60, 60) : Color.WHITE);
        receiptArea.setForeground(textColor);
    }
}
