package dao.customer;

import model.Customer;

import java.util.List;

public interface CustomerDAO {
    // Find All Customer
    List<Customer> findAll();

    // Add Customer
    boolean checkCustomer(String name);

    boolean addCustomer(Customer customer);

    // Update Customer
    Customer findCustomerById(int customerId);

    boolean updateCustomer(Customer customer);

    // Delete Customer
    boolean deleteCustomer(Customer customer);
}
