package bank.service;

import bank.entity.Customer;
import bank.repo.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepo customerRepo;

    @Override
    public void addCustomer(Customer customer) {
        try {
            customerRepo.save(customer);
            System.out.println("Customer added successfully! Account Number: " + customer.getAccNo());
        } catch (Exception e) {
            System.out.println("Error adding customer: " + e.getMessage());
        }
    }

    @Override
    public void updateCustomer(Customer customer) {
        try {
            if (customerRepo.existsById(customer.getAccNo())) {
                customerRepo.save(customer);
                System.out.println("Customer updated successfully! Account Number: " + customer.getAccNo());
            } else {
                System.out.println("Customer with Account Number " + customer.getAccNo() + " not found.");
            }
        } catch (Exception e) {
            System.out.println("Error updating customer: " + e.getMessage());
        }
    }

    @Override
    public void deleteCustomer(Integer accNo) {
        try {
            if (customerRepo.existsById(accNo)) {
                customerRepo.deleteById(accNo);
                System.out.println("Customer deleted successfully! Account Number: " + accNo);
            } else {
                System.out.println("Customer with Account Number " + accNo + " not found.");
            }
        } catch (Exception e) {
            System.out.println("Error deleting customer: " + e.getMessage());
        }
    }

    @Override
    public Customer getCustomerById(Integer accNo) {
        try {
            Optional<Customer> customer = customerRepo.findById(accNo);
            if (customer.isPresent()) {
                System.out.println("Customer retrieved successfully!");
                return customer.get();
            } else {
                System.out.println("Customer with Account Number " + accNo + " not found.");
                return null;
            }
        } catch (Exception e) {
            System.out.println("Error retrieving customer: " + e.getMessage());
            return null;
        }
    }

    @Override
    public List<Customer> getAllCustomers() {
        try {
            List<Customer> customers = customerRepo.findAll();
            System.out.println("Retrieved " + customers.size() + " customers successfully!");
            return customers;
        } catch (Exception e) {
            System.out.println("Error retrieving all customers: " + e.getMessage());
            return null;
        }
    }
}
