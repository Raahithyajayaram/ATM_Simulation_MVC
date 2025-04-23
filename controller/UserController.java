package controller;

import dao.UserDAO;
import model.User;

public class UserController {
    private UserDAO userDAO;

    public UserController() {
        this.userDAO = new UserDAO();
    }

    // Fixed method name and return type
    public User authenticateUser(String username, String pin) {
        return userDAO.authenticate(username, pin);
    }

    public double getBalance(int userId) {
        return userDAO.getBalance(userId);
    }

    public void deposit(int userId, double amount) {
        userDAO.deposit(userId, amount);
    }

    public boolean withdraw(int userId, double amount) {
        return userDAO.withdraw(userId, amount);
    }
}
