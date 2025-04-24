package view;

import controller.UserController;
import model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserLoginFrame extends JFrame {
    private JTextField txtUsername;
    private JPasswordField txtPin;
    private JButton btnLogin;
    private UserController userController;

    public UserLoginFrame() {
        userController = new UserController();

        setTitle("User Login");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());
        getContentPane().setBackground(new Color(40, 44, 52));

        JLabel lblTitle = new JLabel("ATM User Login");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 36));
        lblTitle.setForeground(Color.WHITE);

        JLabel lblUsername = new JLabel("Username:");
        lblUsername.setFont(new Font("Arial", Font.PLAIN, 24));
        lblUsername.setForeground(Color.WHITE);
        txtUsername = new JTextField(20);
        txtUsername.setFont(new Font("Arial", Font.PLAIN, 24));

        JLabel lblPin = new JLabel("PIN:");
        lblPin.setFont(new Font("Arial", Font.PLAIN, 24));
        lblPin.setForeground(Color.WHITE);
        txtPin = new JPasswordField(20);
        txtPin.setFont(new Font("Arial", Font.PLAIN, 24));

        btnLogin = new JButton("Login");
        btnLogin.setFont(new Font("Arial", Font.BOLD, 24));
        btnLogin.setBackground(new Color(0, 153, 204));
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setFocusPainted(false);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 20, 20, 20);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(lblTitle, gbc);

        gbc.gridwidth = 1;
        gbc.gridy++;
        add(lblUsername, gbc);

        gbc.gridx = 1;
        add(txtUsername, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        add(lblPin, gbc);

        gbc.gridx = 1;
        add(txtPin, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        add(btnLogin, gbc);

        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = txtUsername.getText().trim();
                String pin = new String(txtPin.getPassword()).trim();
                System.out.println("Entered Username: '" + username + "'");
System.out.println("Entered PIN: '" + pin + "'");

                if (username.isEmpty() || pin.isEmpty()) {
                    JOptionPane.showMessageDialog(UserLoginFrame.this, "Please enter both username and PIN.");
                    return;
                }

                User user = userController.authenticateUser(username, pin);
                if (user != null) {
                    JOptionPane.showMessageDialog(UserLoginFrame.this, "Login successful!");
                    new UserDashboardFrame(user).setVisible(true);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(UserLoginFrame.this, "Invalid username or PIN.");
                }
                

            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new UserLoginFrame().setVisible(true));
    }
}
