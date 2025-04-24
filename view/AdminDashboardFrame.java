package view;

import controller.AdminController;
import model.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class AdminDashboardFrame extends JFrame {
    private JButton btnAddUser, btnViewUsers, btnDeleteUser;
    private AdminController adminController;

    public AdminDashboardFrame() {
        this.adminController = new AdminController();

        setTitle("Admin Dashboard");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Admin Dashboard", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 48));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(30, 10, 30, 10));

        btnAddUser = new JButton("Add User");
        btnViewUsers = new JButton("View Users");
        btnDeleteUser = new JButton("Delete User");

        Font buttonFont = new Font("Segoe UI", Font.BOLD, 28);
        Color buttonColor = new Color(255, 87, 34);

        JButton[] buttons = {btnAddUser, btnViewUsers, btnDeleteUser};
        for (JButton button : buttons) {
            button.setFont(buttonFont);
            button.setBackground(buttonColor);
            button.setForeground(Color.WHITE);
            button.setFocusPainted(false);
            button.setPreferredSize(new Dimension(300, 80));
        }

        JPanel buttonPanel = new JPanel(new GridLayout(3, 1, 30, 30));
        buttonPanel.setBackground(new Color(38, 50, 56));
        for (JButton button : buttons) {
            buttonPanel.add(button);
        }

        JPanel centerWrapper = new JPanel(new GridBagLayout());
        centerWrapper.setBackground(new Color(38, 50, 56));
        centerWrapper.add(buttonPanel);

        add(titleLabel, BorderLayout.NORTH);
        add(centerWrapper, BorderLayout.CENTER);

        btnAddUser.addActionListener(e -> showAddUserDialog());
        btnViewUsers.addActionListener(e -> showUsersTable());
        btnDeleteUser.addActionListener(e -> showDeleteUserDialog());

        setVisible(true);
    }

    private void showAddUserDialog() {
        JTextField usernameField = new JTextField();
        JTextField pinField = new JTextField();
        JTextField balanceField = new JTextField();

        Object[] message = {
                "Username:", usernameField,
                "PIN:", pinField,
                "Balance:", balanceField
        };

        int option = JOptionPane.showConfirmDialog(this, message, "Add New User", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            String username = usernameField.getText().trim();
            String pin = pinField.getText().trim();
            double balance;
            try {
                balance = Double.parseDouble(balanceField.getText().trim());
                if (adminController.addUser(new User(username, pin, balance))) {
                    JOptionPane.showMessageDialog(this, "User added successfully!");
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to add user.");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid balance value.");
            }
        }
    }

    private void showUsersTable() {
        List<User> users = adminController.viewAllUsers();
        String[] columnNames = {"ID", "Username", "PIN", "Balance"};
        Object[][] rowData = new Object[users.size()][4];

        for (int i = 0; i < users.size(); i++) {
            User user = users.get(i);
            rowData[i][0] = user.getId();
            rowData[i][1] = user.getUsername();
            rowData[i][2] = user.getPin();
            rowData[i][3] = user.getBalance();
        }

        JTable table = new JTable(new DefaultTableModel(rowData, columnNames));
        JScrollPane scrollPane = new JScrollPane(table);

        JOptionPane.showMessageDialog(this, scrollPane, "All Users", JOptionPane.INFORMATION_MESSAGE);
    }

    private void showDeleteUserDialog() {
        String input = JOptionPane.showInputDialog(this, "Enter User ID to delete:");
        if (input != null) {
            try {
                int userId = Integer.parseInt(input.trim());
                boolean deleted = adminController.deleteUser(userId);
                if (deleted) {
                    JOptionPane.showMessageDialog(this, "User deleted successfully.");
                } else {
                    JOptionPane.showMessageDialog(this, "User not found or failed to delete.");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Invalid ID entered.");
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(AdminDashboardFrame::new);
    }
}
