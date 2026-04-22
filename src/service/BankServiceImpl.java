package service;

import domain.Account;
import repository.AccountRepository;

import java.util.UUID;

public class BankServiceImpl implements BankService {
    private final AccountRepository accountRepository = new AccountRepository();


    @Override
    public String openAccount(String name, String email, String accountType) {
        String customerId = UUID.randomUUID().toString();
        String accountNumber = getAccountNumber();
        Account ac = new Account(accountNumber,accountType,customerId,(double) 0);
        accountRepository.save(ac);
        return accountNumber;
    }

    private String getAccountNumber() {
        int size = accountRepository.findAll().size()+1;
        String accountNumber  = String.format("AC06d"+size);
        return accountNumber;
    }
}