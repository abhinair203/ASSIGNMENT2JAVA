package assignment2;

import java.text.DecimalFormat;

public class Account {
    private String accountName;
    private int accountNumber;
    private double accountBalance;

    public Account() {}
    public Account(String accountName, int accountNumber, double accountBalance) {
        this.accountName = validateAccountName(accountName) ? accountName : "Default Account";
        this.accountNumber = validateAccountNumber(accountNumber) ? accountNumber : 100000; // default 6-digit number
        this.accountBalance = validateAccountBalance(accountBalance) ? accountBalance : 0.0;
    }
    public String getAccountName() {
        return this.accountName;
    }
    public boolean setAccountName(String accountName) {
        if (validateAccountName(accountName)) {
            this.accountName = accountName;
            return true;
        }
        return false;
    }
    public int getAccountNumber() {
        return this.accountNumber;
    }
    public boolean setAccountNumber(int accountNumber) {
        if (validateAccountNumber(accountNumber)) {
            this.accountNumber = accountNumber;
            return true;
        }
        return false;
    }
    public double getAccountBalance() {
        return this.accountBalance;
    }
    public boolean setAccountBalance(double value) {
        if (validateAccountBalance(value)) {
            this.accountBalance = value;
            return true;
        }
        return false;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Account account = (Account) obj;
        return accountNumber == account.accountNumber && Double.compare(account.accountBalance, accountBalance) == 0 && accountName.equals(account.accountName);
    }
    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("#.00");
        return "Account name: " + this.accountName + ", Account number: " + this.accountNumber + ", Account balance: $" + df.format(this.accountBalance);
    }
    private boolean validateAccountName(String accountName) {
        return accountName != null && accountName.length() >= 4 && accountName.matches("[a-zA-Z-' ]*");
    }
    private boolean validateAccountNumber(int accountNumber) {
        int length = String.valueOf(accountNumber).length();
        return accountNumber >= 0 && (length >= 5 && length <= 9);
    }
    private boolean validateAccountBalance(double accountBalance) {
        DecimalFormat df = new DecimalFormat("#.00");
        String balance = df.format(accountBalance);
        return !balance.contains(".");
    }
}
