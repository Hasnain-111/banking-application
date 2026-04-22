package domain;

import java.time.LocalDateTime;

public class transaction {
    private String id;
    private String accountNumber;
    private Type type;
    private double amount;
    private String note;
    private LocalDateTime timeStamp;

    public transaction(String id,
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
}
