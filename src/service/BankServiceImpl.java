package service;

import domain.Account;

import java.util.UUID;

public class BankServiceImpl implements BankService {


    @Override
    public String openAccount(String name, String email, String accountType) {
        String customerId = UUID.randomUUID().toString();
        String accountNumber  = UUID.randomUUID().toString();
        Account ac = new Account(accountNumber,accountType,customerId,(double)0);
        return "";
    }
}