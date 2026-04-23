package service;

import domain.Account;
import domain.Transaction;
import domain.Type;
import repository.AccountRepository;
import repository.TransactionRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class BankServiceImpl implements BankService {
    private final AccountRepository accountRepository = new AccountRepository();
    private final TransactionRepository transactionRepository = new TransactionRepository();


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

    @Override
    public void deposit(String accountNumber, double amount, String note) {
        Account account = accountRepository.FindByNumber(accountNumber).orElseThrow(()->new RuntimeException("Account not found"+accountNumber));
        account.setBalance(account.getBalance()+amount);
        Transaction transaction = new Transaction(UUID.randomUUID().toString(), LocalDateTime.now(),note,amount, Type.Deposit,accountNumber);
        transactionRepository.add(transaction);
    }

    @Override
    public void withDraw(String accountNumber, double amount, String withDrawl) {
        Account account = accountRepository.FindByNumber(accountNumber).orElseThrow(()->new RuntimeException("Account not found"+accountNumber));
        account.setBalance(account.getBalance()-amount);
        Transaction transaction = new Transaction(UUID.randomUUID().toString(),LocalDateTime.now(),"Withdraw",amount,Type.withdraw,accountNumber);
        transactionRepository.add(transaction);
    }

    private String getAccountNumber() {
        int size = accountRepository.findAll().size()+1;
        return String.format("AC%06d",size);
    }
}