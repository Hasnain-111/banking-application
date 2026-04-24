package service;

import domain.Account;
import domain.Customer;
import domain.Transaction;
import domain.Type;
import repository.AccountRepository;
import repository.CustomerRepository;
import repository.TransactionRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class BankServiceImpl implements BankService {
    private final AccountRepository accountRepository = new AccountRepository();
    private final TransactionRepository transactionRepository = new TransactionRepository();
    private final CustomerRepository customerRepository = new CustomerRepository();


    @Override
    public String openAccount(String name, String email, String accountType) {
        String customerId = UUID.randomUUID().toString();
        Customer c  = new Customer(name,customerId,email);
        customerRepository.save(c);
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
        if(account.getBalance() <amount){
            throw new RuntimeException("Insufficient Balance:");
        }
        account.setBalance(account.getBalance()-amount);
        Transaction transaction = new Transaction(UUID.randomUUID().toString(),LocalDateTime.now(),"Withdraw",amount,Type.withdraw,accountNumber);
        transactionRepository.add(transaction);
    }

    @Override
    public void transfer(String from, String to, double amount, String transfer) {
        if(from.equals(to)){
            System.out.println("Amount cannot be transfered to self account");
        }
        Account fromAcc = accountRepository.FindByNumber(from).orElseThrow(()->new RuntimeException("Account not found"));
        Account toAcc =accountRepository.FindByNumber(to).orElseThrow(()->new RuntimeException("Accont not found"));
        if(fromAcc.getBalance()<amount){
            System.out.println("Insufficent Balance");
        }

        fromAcc.setBalance(fromAcc.getBalance()-amount);
        toAcc.setBalance(toAcc.getBalance()+amount);

        Transaction fromTransaction = new Transaction(UUID.randomUUID().toString(),LocalDateTime.now(),transfer,amount,Type.transferIn,fromAcc.getAccountNumber());

        Transaction toTransaction =new Transaction(UUID.randomUUID().toString(),LocalDateTime.now(),transfer,amount,Type.transferOut,toAcc.getAccountNumber());
    }

    @Override
    public List<Transaction> getStatement(String account) {
        return  transactionRepository.findStatement(account).stream().sorted(Comparator.comparing(Transaction::getTimeStamp)).collect(Collectors.toList());
    }

    @Override
    public List<Account> searchByCustomerName(String q) {
        String query = (q== null)?"":q.toLowerCase();
        List<Account> result = new ArrayList<>();
        for(Customer c:customerRepository.findAll()){
         if(c.getName().toLowerCase().contains(query)){
             result.addAll(accountRepository.findByCustomerId(c.getId()));
         }
        }
        result.sort(Comparator.comparing(Account::getAccountNumber));
        return result;
    }

    private String getAccountNumber() {
        int size = accountRepository.findAll().size()+1;
        return String.format("AC%06d",size);
    }
}