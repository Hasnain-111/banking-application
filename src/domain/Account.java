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
}
