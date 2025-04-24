package model;

public class User {
    private int id;
    private String username;
    private String pin;
    private double balance;

    public User(String username, String pin, double balance) {
        this.username = username;
        this.pin = pin;
        this.balance = balance;
    }

    public User(int id, String username, String pin, double balance) {
        this.id = id;
        this.username = username;
        this.pin = pin;
        this.balance = balance;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPin() {
        return pin;
    }

    public double getBalance() {
        return balance;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
