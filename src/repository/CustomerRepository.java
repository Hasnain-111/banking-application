package repository;

import domain.Customer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomerRepository {
    private final Map<String, Customer> customerByid = new HashMap<>();

    public List<Customer> findAll() {
        return new ArrayList<>(customerByid.values());
    }

    public void save(Customer c) {
        customerByid.put(c.getId(),c);
    }
}
