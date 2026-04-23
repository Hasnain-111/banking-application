package domain;

public class Account {
    private String accountNumber;
    private String customerId;
    private String accountType;
    private double balance;

    public Account(String accountNumber, String accountType, String customerId, double balance) {
        this.accountNumber = accountNumber;
        this.accountType = accountType;
        this.customerId = customerId;
        this.balance = balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public String getAccountType() {
        return accountType;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
}
