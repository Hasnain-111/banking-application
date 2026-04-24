package service;

import domain.Account;
import domain.Customer;
import domain.Transaction;
import domain.Type;
import exceptions.AccountNotFoundException;
import exceptions.InsufficientFundException;
import exceptions.ValidationException;
import repository.AccountRepository;
import repository.CustomerRepository;
import repository.TransactionRepository;
import util.Validation;

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

    private final Validation<String> validateName = name -> {
        if (name == null || name.isBlank()) {
            throw new ValidationException("Name cannot be empty");
        }
    };

    private final Validation<String> validateEmail = email -> {
        if (email == null || !email.contains("@")) {
            throw new ValidationException("Invalid email format");
        }
    };

    private final Validation<String> validateType = type -> {
        if (!(type.equalsIgnoreCase("saving") || type.equalsIgnoreCase("current"))) {
            throw new ValidationException("Type must be 'saving' or 'current'");
        }
    };

    @Override
    public String openAccount(String name, String email, String accountType) {
        validateName.validation(name);
        validateEmail.validation(email);
        validateType.validation(accountType);
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
        Account account = accountRepository.FindByNumber(accountNumber).orElseThrow(()->new AccountNotFoundException("Account not found"+accountNumber));
        account.setBalance(account.getBalance()+amount);
        Transaction transaction = new Transaction(UUID.randomUUID().toString(), LocalDateTime.now(),note,amount, Type.Deposit,accountNumber);
        transactionRepository.add(transaction);
    }

    @Override
    public void withDraw(String accountNumber, double amount, String withDrawl) {
        Account account = accountRepository.FindByNumber(accountNumber).orElseThrow(()->new RuntimeException("Account not found"+accountNumber));
        if(amount<0){
            throw new InsufficientFundException("amount must be postive");
        }
        if(account.getBalance() <amount){
            throw new InsufficientFundException("Insufficient Balance:");
        }
        account.setBalance(account.getBalance()-amount);
        Transaction transaction = new Transaction(UUID.randomUUID().toString(),LocalDateTime.now(),"Withdraw",amount,Type.withdraw,accountNumber);
        transactionRepository.add(transaction);
    }

    @Override
    public void transfer(String from, String to, double amount, String note) {

        // 🔹 Validate inputs
        if (from == null || to == null) {
            throw new ValidationException("Account number cannot be null");
        }

        if (from.equals(to)) {
            throw new ValidationException("Cannot transfer to the same account");
        }

        if (amount <= 0) {
            throw new ValidationException("Amount must be positive");
        }

        // 🔹 Fetch accounts
        Account fromAcc = accountRepository.FindByNumber(from)
                .orElseThrow(() -> new AccountNotFoundException("From account not found: " + from));

        Account toAcc = accountRepository.FindByNumber(to)
                .orElseThrow(() -> new AccountNotFoundException("To account not found: " + to));

        // 🔹 Check balance
        if (fromAcc.getBalance() < amount) {
            throw new InsufficientFundException("Insufficient balance");
        }

        // 🔹 Perform transfer
        fromAcc.setBalance(fromAcc.getBalance() - amount);
        toAcc.setBalance(toAcc.getBalance() + amount);

        // 🔹 Record transactions
        Transaction fromTransaction = new Transaction(
                UUID.randomUUID().toString(),
                LocalDateTime.now(),
                note,
                amount,
                Type.transferOut,
                fromAcc.getAccountNumber()
        );

        Transaction toTransaction = new Transaction(
                UUID.randomUUID().toString(),
                LocalDateTime.now(),
                note,
                amount,
                Type.transferIn,
                toAcc.getAccountNumber()
        );

        transactionRepository.add(fromTransaction);
        transactionRepository.add(toTransaction);
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