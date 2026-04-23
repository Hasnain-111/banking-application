package repository;

import domain.Transaction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TransactionRepository {
    private final Map<String, List<Transaction>> txByAccountNumber = new HashMap<>();

    public void add(Transaction transaction) {
        txByAccountNumber.computeIfAbsent(transaction.getAccountNumber(), k->new ArrayList<>()).add(transaction);
    }
}
