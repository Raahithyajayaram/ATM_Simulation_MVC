package model;

import java.util.ArrayList;
import java.util.List;

public class Account {
    private String accountNumber;
    private String pin;
    private double balance;
    private List<String> transactions;

    public Account(String accountNumber, String pin, double balance) {
        this.accountNumber = accountNumber;
        this.pin = pin;
        this.balance = balance;
        this.transactions = new ArrayList<>();
    }

    public boolean validatePin(String inputPin) {
        return this.pin.equals(inputPin);
    }

    public double getBalance() {
        return balance;
    }

    public void withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            transactions.add("Withdrawn: " + amount);
        } else {
            transactions.add("Failed withdrawal: Insufficient funds");
        }
    }

    public void addTransaction(String detail) {
        transactions.add(detail);
    }

    public List<String> getRecentTransactions(int count) {
        int start = Math.max(0, transactions.size() - count);
        return transactions.subList(start, transactions.size());
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void deposit(double amount) {
        balance += amount;
        transactions.add("Deposited: " + amount);
    }
}
