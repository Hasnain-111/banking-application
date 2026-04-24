package service;

import domain.Account;
import domain.Transaction;

import java.util.List;

public interface BankService {
    String openAccount(String name,String email,String accountType);
    List<Account> listAccount();

    void deposit(String accountNumber, double amount, String note);

    void withDraw(String accountNumber, double amount, String withDrawl);

    void transfer(String from, String to, double amount, String transfer);

    List<Transaction> getStatement(String account);

    List<Account> searchByCustomerName(String q);
}
