package bank.controller;

import bank.entity.Customer;
import bank.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/add")
    public ResponseEntity<String> addCustomer(@RequestBody Customer customer) {
        try {
            customerService.addCustomer(customer);
            return ResponseEntity.ok("Customer added successfully with Account Number: " + customer.getAccNo());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error adding customer: " + e.getMessage());
        }
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateCustomer(@RequestBody Customer customer) {
        try {
            customerService.updateCustomer(customer);
            return ResponseEntity.ok("Customer updated successfully!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error updating customer: " + e.getMessage());
        }
    }

    @DeleteMapping("/delete/{accNo}")
    public ResponseEntity<String> deleteCustomer(@PathVariable Integer accNo) {
        try {
            customerService.deleteCustomer(accNo);
            return ResponseEntity.ok("Customer deleted successfully!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error deleting customer: " + e.getMessage());
        }
    }

    @GetMapping("/getById/{accNo}")
    public ResponseEntity<?> getCustomerById(@PathVariable Integer accNo) {
        try {
            Customer customer = customerService.getCustomerById(accNo);
            if (customer != null) {
                return ResponseEntity.ok(customer);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Customer not found with Account Number: " + accNo);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error retrieving customer: " + e.getMessage());
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAllCustomers() {
        try {
            List<Customer> customers = customerService.getAllCustomers();
            if (customers != null && !customers.isEmpty()) {
                return ResponseEntity.ok(customers);
            } else {
                return ResponseEntity.ok("No customers found.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error retrieving customers: " + e.getMessage());
        }
    }
}
