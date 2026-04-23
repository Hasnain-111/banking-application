package domain;

import java.time.LocalDateTime;

public class Transaction {
    private String id;
    private String accountNumber;
    private Type type;
    private double amount;
    private String note;
    private LocalDateTime timeStamp;

    public Transaction(String id,
                       LocalDateTime timeStamp,
                       String note,
                       double amount,
                       Type type,
                       String accountNumber) {
        this.id = id;
        this.timeStamp = timeStamp;
        this.note = note;
        this.amount = amount;
        this.type = type;
        this.accountNumber = accountNumber;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public String getNote() {
        return note;
    }

    public double getAmount() {
        return amount;
    }

    public Type getType() {
        return type;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getId() {
        return id;
    }
}
