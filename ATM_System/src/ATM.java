import java.util.*;

// Class to represent a Transaction
class Transaction {
    private String type;
    private double amount;

    public Transaction(String type, double amount) {
        this.type = type;
        this.amount = amount;
    }

    public String getTransactionDetails() {
        return type + ": $" + amount;
    }
}

// Class to manage bank account operations
class BankAccount {
    private double balance;
    private List<Transaction> transactions = new ArrayList<>();

    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
        transactions.add(new Transaction("Deposit", amount));
        System.out.println("✅ Deposit successful!");
    }

    public boolean withdraw(double amount) {
        if (amount > balance) {
            System.out.println("❌ Insufficient funds!");
            return false;
        }
        balance -= amount;
        transactions.add(new Transaction("Withdraw", amount));
        System.out.println("✅ Withdrawal successful!");
        return true;
    }

    public void printTransactionHistory() {
        if (transactions.isEmpty()) {
            System.out.println("No transactions yet.");
            return;
        }
        System.out.println("\n📜 Transaction History:");
        for (Transaction t : transactions) {
            System.out.println(t.getTransactionDetails());
        }
    }
}

// Class to store user info and validate PIN
class User {
    private String username;
    private String pin;
    private BankAccount account;

    public User(String username, String pin, double initialBalance) {
        this.username = username;
        this.pin = pin;
        this.account = new BankAccount(initialBalance);
    }

    public boolean validatePin(String enteredPin) {
        return this.pin.equals(enteredPin);
    }

    public String getUsername() {
        return username;
    }

    public BankAccount getAccount() {
        return account;
    }
}

public class ATM {
    private static Map<String, User> users = new HashMap<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Sample users
        users.put("user1", new User("user1", "1234", 1000));
        users.put("user2", new User("user2", "5678", 2000));

        System.out.print("🔐 Enter Username: ");
        String username = scanner.next();

        if (!users.containsKey(username)) {
            System.out.println("❌ User not found! Exiting...");
            return;
        }

        User currentUser = users.get(username);
        System.out.print("🔑 Enter PIN: ");
        String pin = scanner.next();

        if (!currentUser.validatePin(pin)) {
            System.out.println("❌ Incorrect PIN! Exiting...");
            return;
        }

        BankAccount account = currentUser.getAccount();
        System.out.println("\n✅ Welcome, " + username + "!");

        while (true) {
            System.out.println("\n🏦 ATM Menu:");
            System.out.println("1️⃣ Check Balance");
            System.out.println("2️⃣ Deposit Money");
            System.out.println("3️⃣ Withdraw Money");
            System.out.println("4️⃣ Transaction History");
            System.out.println("5️⃣ Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("💰 Balance: $" + account.getBalance());
                    break;
                case 2:
                    System.out.print("💵 Enter deposit amount: ");
                    double depositAmount = scanner.nextDouble();
                    account.deposit(depositAmount);
                    break;
                case 3:
                    System.out.print("💸 Enter withdrawal amount: ");
                    double withdrawAmount = scanner.nextDouble();
                    account.withdraw(withdrawAmount);
                    break;
                case 4:
                    account.printTransactionHistory();
                    break;
                case 5:
                    System.out.println("👋 Thank you for using the ATM!");
                    scanner.close();
                    return;
                default:
                    System.out.println("⚠️ Invalid option! Try again.");
            }
        }
    }
}