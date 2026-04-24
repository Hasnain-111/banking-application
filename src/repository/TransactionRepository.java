package repository;

import domain.Transaction;

import java.util.*;

public class TransactionRepository {
    private final Map<String, List<Transaction>> txByAccountNumber = new HashMap<>();

    public void add(Transaction transaction) {
        txByAccountNumber.computeIfAbsent(transaction.getAccountNumber(), k->new ArrayList<>()).add(transaction);
    }

    public List<Transaction> findStatement(String account) {
        return new ArrayList<>(txByAccountNumber.getOrDefault(account, Collections.emptyList()));
    }
}
