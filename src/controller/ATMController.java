package controller;

import model.Account;
import java.util.HashMap;
import java.util.Map;


public class ATMController {
    private Map<String, Account> accounts;
    private Account currentAccount;

    public ATMController() {
        accounts = new HashMap<>();

        // Dummy accounts for simulation
        accounts.put("123456", new Account("123456", "1234", 10000.0));
        accounts.put("987654", new Account("987654", "4321", 5000.0));
    }

    public boolean login(String accNum, String pin) {
        if (accounts.containsKey(accNum)) {
            Account acc = accounts.get(accNum);
            if (acc.validatePin(pin)) {
                currentAccount = acc;
                System.out.println("✅ Login successful!");
                return true;
            }
        }
        System.out.println("❌ Invalid account number or PIN.");
        return false;
    }
     // Get balance (used in GUI)
    public double getCurrentBalance() {
        return currentAccount != null ? currentAccount.getBalance() : 0.0;
    }

    // Get last few transactions for Mini Statement
    public java.util.List<String> getTransactionHistory() {
        return currentAccount != null ? currentAccount.getRecentTransactions(5) : new java.util.ArrayList<>();
    }


    public void checkBalance() {
        if (currentAccount != null) {
            System.out.println("💰 Balance: ₹" + currentAccount.getBalance());
        }
    }

    public void withdraw(double amount) {
        if (currentAccount != null) {
            boolean success = currentAccount.withdraw(amount);
            if (success) {
                System.out.println("💸 Withdrawal of ₹" + amount + " processed.");
                currentAccount.printReceipt(amount); // Show receipt
            } else {
                System.out.println("❌ Transaction Failed: Insufficient balance.");
            }
        }
    }
    

    public void printMiniStatement() {
        if (currentAccount != null) {
            System.out.println("📄 Last transactions:");
            for (String txn : currentAccount.getRecentTransactions(5)) {
                System.out.println("  • " + txn);
            }
        }
    }

    public void logout() {
        currentAccount = null;
        System.out.println("🔒 Session terminated. Card ejected.");
    }
    public String generateReceipt(double amount) {
        if (currentAccount != null) {
            StringBuilder receipt = new StringBuilder();
            receipt.append("\n==============================\n");
            receipt.append(" ATM RECEIPT\n");
            receipt.append("==============================\n");
            receipt.append("Account No : ****" + String.valueOf(currentAccount.getAccountNumber()).substring(2) + "\n");
            receipt.append("Transaction: Withdrawal\n");
            receipt.append(String.format("Amount : ₹ %.2f\n", amount));
            receipt.append(String.format("Balance : ₹ %.2f\n", currentAccount.getBalance()));
            receipt.append("Date : " + java.time.LocalDateTime.now() + "\n");
            receipt.append("==============================\n");
    
            return receipt.toString();
        }
        return "❌ Receipt Generation Failed";
    }
    
}
