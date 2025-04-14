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

    public boolean withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            addTransaction("Withdrawn: ₹" + amount);
            return true;
        } else {
            return false;
        }
    }
    
    public void printReceipt(double amount) { System.out.println("\n=============================="); 
    System.out.println(" ATM RECEIPT"); System.out.println("=============================="); 
    System.out.println("Account No : ****" + String.valueOf(accountNumber).substring(2)); 
    System.out.println("Transaction: Withdrawal"); 
    System.out.printf("Amount : Rs %.2f\n", amount); 
    System.out.printf("Balance : Rs %.2f\n", balance);
    System.out.println("Date : " + java.time.LocalDateTime.now());
    System.out.println("==============================\n"); }
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
