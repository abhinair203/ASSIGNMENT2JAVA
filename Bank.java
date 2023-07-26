package assignment2;

import java.util.HashMap;
import java.util.Map;

public class Bank {
    public static enum BranchLocations {LOCATION1, LOCATION2};
    private String bankName;
    private BranchLocations branchLocation;
    private Map<Integer, Account> accounts;

    public Bank() {
        this.bankName = "Default Bank";
        this.branchLocation = BranchLocations.LOCATION1;
        this.accounts = new HashMap<>();
    }

    public Bank(String bankName, String branchLocation) {
        this.bankName = validateBankName(bankName) ? bankName : "Default Bank";
        try {
            this.branchLocation = BranchLocations.valueOf(branchLocation);
        } catch (IllegalArgumentException e) {
            this.branchLocation = BranchLocations.LOCATION1;
        }
        this.accounts = new HashMap<>();
    }

    public Bank(String bankName, BranchLocations branchLocation) {
        this.bankName = validateBankName(bankName) ? bankName : "Default Bank";
        this.branchLocation = branchLocation;
        this.accounts = new HashMap<>();
    }

    public String getBankName() {
        return this.bankName;
    }

    public boolean setBankName(String bankName) {
        if (validateBankName(bankName)) {
            this.bankName = bankName;
            return true;
        }
        return false;
    }

    public boolean setBranchLocation(String branchLocation) {
        try {
            this.branchLocation = BranchLocations.valueOf(branchLocation);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    public String getBranchLocation() {
        return this.branchLocation.toString();
    }

    public boolean addAccount(Account account) {
        if (!accounts.containsKey(account.getAccountNumber())) {
            accounts.put(account.getAccountNumber(), account);
            return true;
        }
        return false;
    }

    public boolean addAccount(String accountName, int accountNumber, double accountBalance) {
        if (!accounts.containsKey(accountNumber)) {
            accounts.put(accountNumber, new Account(accountName, accountNumber, accountBalance));
            return true;
        }
        return false;
    }

    public Account viewAccount(int accountNumber) {
        return accounts.getOrDefault(accountNumber, new Account());
    }

    public Account viewAccount(byte index) {
        return accounts.values().stream().skip(index).findFirst().orElse(new Account());
    }

    public boolean modifyAccount(int accountNumber, String accountName) {
        Account account = accounts.get(accountNumber);
        if (account != null && account.setAccountName(accountName)) {
            return true;
        }
        return false;
    }

    public boolean modifyAccount(int accountNumber, double accountBalance) {
        Account account = accounts.get(accountNumber);
        if (account != null && account.setAccountBalance(accountBalance)) {
            return true;
        }
        return false;
    }

    public boolean modifyAccount(int accountNumber, String accountName, double accountBalance) {
        Account account = accounts.get(accountNumber);
        if (account != null && account.setAccountName(accountName) && account.setAccountBalance(accountBalance)) {
            return true;
        }
        return false;
    }

    public boolean modifyAccount(byte index, String accountName) {
        Account account = viewAccount(index);
        if (!account.getAccountName().equals("") && account.setAccountName(accountName)) {
            return true;
        }
        return false;
    }

    public boolean modifyAccount(byte index, double accountBalance) {
        Account account = viewAccount(index);
        if (!account.getAccountName().equals("") && account.setAccountBalance(accountBalance)) {
            return true;
        }
        return false;
    }

    public boolean modifyAccount(byte index, String accountName, double accountBalance) {
        Account account = viewAccount(index);
        if (!account.getAccountName().equals("") && account.setAccountName(accountName) && account.setAccountBalance(accountBalance)) {
            return true;
        }
        return false;
    }

    public boolean deleteAccount(int accountNumber) {
        return accounts.remove(accountNumber) != null;
    }

    public boolean deleteAccount(byte index) {
        int accountNumber = (int) accounts.keySet().toArray()[index];
        return accounts.remove(accountNumber) != null;
    }

    private boolean validateBankName(String bankName) {
        return bankName != null && bankName.length() >= 8 && bankName.matches("[a-zA-Z0-9& ]*");
    }
}
