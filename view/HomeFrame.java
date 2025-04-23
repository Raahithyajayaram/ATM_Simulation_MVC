package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomeFrame extends JFrame {
    public HomeFrame() {
        setTitle("ATM System - Home");
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Fullscreen
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Theme colors
        Color primaryColor = new Color(0x1E1E2F);
        Color accentColor = new Color(0x4DB8FF);
        Color textColor = Color.WHITE;

        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(primaryColor);
        mainPanel.setLayout(new GridBagLayout());

        JLabel title = new JLabel("Welcome to the ATM System");
        title.setFont(new Font("Arial", Font.BOLD, 36));
        title.setForeground(textColor);

        JButton adminLogin = new JButton("Admin Login");
        JButton userLogin = new JButton("User Login");

        for (JButton button : new JButton[]{adminLogin, userLogin}) {
            button.setFont(new Font("Arial", Font.BOLD, 24));
            button.setBackground(accentColor);
            button.setForeground(textColor);
            button.setFocusPainted(false);
            button.setPreferredSize(new Dimension(250, 60));
        }

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 20, 20, 20);
        gbc.gridx = 0;
        gbc.gridy = 0;
        mainPanel.add(title, gbc);

        gbc.gridy++;
        mainPanel.add(adminLogin, gbc);

        gbc.gridy++;
        mainPanel.add(userLogin, gbc);

        adminLogin.addActionListener(e -> {
            dispose();
            new AdminLoginFrame().setVisible(true);
        });

        userLogin.addActionListener(e -> {
            dispose();
            new UserLoginFrame().setVisible(true);
        });

        add(mainPanel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new HomeFrame().setVisible(true);
        });
    }
}
