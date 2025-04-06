package view;

import controller.ATMController;

import java.util.Scanner;

public class ATMView {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ATMController controller = new ATMController();

        System.out.println("💳 Welcome to the ATM Simulation");

        System.out.print("👉 Enter Account Number: ");
        String accNum = scanner.nextLine();

        System.out.print("🔑 Enter PIN: ");
        String pin = scanner.nextLine();

        if (controller.login(accNum, pin)) {
            boolean sessionActive = true;

            while (sessionActive) {
                System.out.println("\n📋 Menu:");
                System.out.println("1. Check Balance");
                System.out.println("2. Withdraw Cash");
                System.out.println("3. Mini Statement");
                System.out.println("4. Exit");
                System.out.print("Choose an option: ");

                int choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        controller.checkBalance();
                        break;
                    case 2:
                        System.out.print("Enter amount to withdraw: ");
                        double amount = scanner.nextDouble();
                        controller.withdraw(amount);
                        break;
                    case 3:
                        controller.printMiniStatement();
                        break;
                    case 4:
                        controller.logout();
                        sessionActive = false;
                        break;
                    default:
                        System.out.println("❌ Invalid option. Try again.");
                }
            }
        } else {
            System.out.println("❌ Authentication failed. Exiting.");
        }

        scanner.close();
    }
}
