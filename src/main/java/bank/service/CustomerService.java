package bank.service;

import bank.entity.Customer;
import java.util.List;

public interface CustomerService {
    void addCustomer(Customer customer);
    void updateCustomer(Customer customer);
    void deleteCustomer(Integer accNo);
    Customer getCustomerById(Integer accNo);
    List<Customer> getAllCustomers();
}
