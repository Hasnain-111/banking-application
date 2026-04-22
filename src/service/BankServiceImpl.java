package service;

import domain.Account;
import repository.AccountRepository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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

    @Override
    public List<Account> listAccount() {
        return accountRepository.findAll().stream().collect(Collectors.toList());
    }

    private String getAccountNumber() {
        int size = accountRepository.findAll().size()+1;
        return String.format("AC%06d",size);
    }
}