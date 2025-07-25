package business.customer.imp;

import business.customer.CustomerBusiness;
import dao.customer.CustomerDAO;
import dao.customer.imp.CustomerDAOImp;
import model.Customer;

import java.util.List;

public class CustomerBusinessImp implements CustomerBusiness {

    private CustomerDAO customerDAO;

    public CustomerBusinessImp(){
        customerDAO = new CustomerDAOImp();
    }

    // Find All
    @Override
    public List<Customer> findAll() {
        return customerDAO.findAll();
    }

    // Add Customer
    @Override
    public boolean checkCustomer(String name) {
        return customerDAO.checkCustomer(name);
    }

    @Override
    public boolean addCustomer(Customer customer) {
        return customerDAO.addCustomer(customer);
    }

    // Update Customer
    @Override
    public Customer findCustomerById(int customerId) {
        return customerDAO.findCustomerById(customerId);
    }

    @Override
    public boolean updateCustomer(Customer customer) {
        return customerDAO.updateCustomer(customer);
    }

    // Delete Customer
    @Override
    public boolean deleteCustomer(Customer customerId) {
        return customerDAO.deleteCustomer(customerId);
    }
}
