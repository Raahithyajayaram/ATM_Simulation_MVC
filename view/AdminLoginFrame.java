package view;

import controller.AdminController;

import javax.swing.*;
import java.awt.*;

public class AdminLoginFrame extends JFrame {
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLogin, btnBack;
    private AdminController adminController;

    public AdminLoginFrame() {
        setTitle("Admin Login");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        adminController = new AdminController(); 

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(20, 30, 60));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel lblTitle = new JLabel("Admin Login Portal");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 32));
        lblTitle.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(lblTitle, gbc);

        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridy++;
        JLabel lblUsername = new JLabel("Username:");
        lblUsername.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        lblUsername.setForeground(Color.WHITE);
        panel.add(lblUsername, gbc);

        gbc.gridx = 1;
        txtUsername = new JTextField(20);
        txtUsername.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        panel.add(txtUsername, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        JLabel lblPassword = new JLabel("Password:");
        lblPassword.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        lblPassword.setForeground(Color.WHITE);
        panel.add(lblPassword, gbc);

        gbc.gridx = 1;
        txtPassword = new JPasswordField(20);
        txtPassword.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        panel.add(txtPassword, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        btnLogin = new JButton("Login");
        styleButton(btnLogin);
        panel.add(btnLogin, gbc);

        gbc.gridx = 1;
        btnBack = new JButton("Back");
        styleButton(btnBack);
        panel.add(btnBack, gbc);

        add(panel);

        btnLogin.addActionListener(e -> {
            String username = txtUsername.getText().trim();
            String password = new String(txtPassword.getPassword()).trim();

            if (username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter both username and password.", "Missing Info", JOptionPane.WARNING_MESSAGE);
                return;
            }

            boolean isAuthenticated = adminController.authenticateAdmin(username, password);
            if (isAuthenticated) {
                JOptionPane.showMessageDialog(this, "Login successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                dispose(); // close login frame
                new AdminDashboardFrame().setVisible(true); 
            } else {
                JOptionPane.showMessageDialog(this, "Invalid username or password.", "Login Failed", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnBack.addActionListener(e -> {
            dispose();
            new HomeFrame(); 
        });

        setVisible(true);
    }

    private void styleButton(JButton button) {
        button.setBackground(new Color(0, 120, 215));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Segoe UI", Font.BOLD, 16));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(AdminLoginFrame::new);
    }
}
