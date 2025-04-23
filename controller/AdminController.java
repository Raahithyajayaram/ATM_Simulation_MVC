package controller;

import dao.AdminDAO;
import model.User;
import model.Admin;
import java.util.List;

public class AdminController {
    private AdminDAO adminDAO;

    public AdminController() {
        this.adminDAO = new AdminDAO();
    }

    // View all users
    public List<User> viewAllUsers() {
        return adminDAO.getAllUsers();
    }

    // Add a new user
    public boolean addUser(User user) {
        return adminDAO.addUser(user);
    }

    // Update user balance
    public boolean updateUserBalance(int userId, double newBalance) {
        return adminDAO.updateUserBalance(userId, newBalance);
    }

    // Delete a user
    public boolean deleteUser(int userId) {
        return adminDAO.deleteUser(userId);
    }

    // ✅ Authenticate admin credentials
    public boolean authenticateAdmin(String username, String password) {
        Admin admin = adminDAO.getAdminByUsername(username);
        if (admin != null && admin.getPassword().equals(password)) {
            return true;
        }
        return false;
    }
}
