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

    public void checkBalance() {
        if (currentAccount != null) {
            System.out.println("💰 Balance: ₹" + currentAccount.getBalance());
        }
    }

    public void withdraw(double amount) {
        if (currentAccount != null) {
            currentAccount.withdraw(amount);
            System.out.println("💸 Withdrawal of ₹" + amount + " processed.");
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
}
