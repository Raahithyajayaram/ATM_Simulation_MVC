package dao;

import model.Database;
import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {
    public UserDAO() {}

    public User authenticate(String username, String pin) {
      System.out.println("Authenticating with: " + username + " / " + pin);
        String query = "SELECT * FROM users WHERE username = ? AND pin = ?";
        try (Connection conn = Database.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, username);
            stmt.setString(2, pin);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new User(
                    rs.getInt("id"),
                    rs.getString("username"),
                    rs.getString("pin"),
                    rs.getDouble("balance")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        

        return null;
    }

    public double getBalance(int userId) {
        String query = "SELECT balance FROM users WHERE id = ?";
        try (Connection conn = Database.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getDouble("balance");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0.0;
    }

    public void deposit(int userId, double amount) {
        String query = "UPDATE users SET balance = balance + ? WHERE id = ?";
        try (Connection conn = Database.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setDouble(1, amount);
            stmt.setInt(2, userId);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean withdraw(int userId, double amount) {
        double currentBalance = getBalance(userId);
        if (currentBalance < amount) return false;

        String query = "UPDATE users SET balance = balance - ? WHERE id = ?";
        try (Connection conn = Database.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setDouble(1, amount);
            stmt.setInt(2, userId);
            stmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
