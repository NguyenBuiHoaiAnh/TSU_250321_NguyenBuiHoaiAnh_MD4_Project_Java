package dao.customer.imp;

import dao.customer.CustomerDAO;
import model.Customer;
import util.ConnectionDB;

import java.lang.reflect.Type;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAOImp implements CustomerDAO {

    // Find All
    @Override
    public List<Customer> findAll() {
        Connection conn = null;
        CallableStatement callSt = null;
        List<Customer> customerList = null;

        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call find_all_customer()}");
            ResultSet rs = callSt.executeQuery();

            customerList = new ArrayList<>();
            while (rs.next()) {
                Customer customer = new Customer();
                customer.setId(rs.getInt("id"));
                customer.setName(rs.getString("name"));
                customer.setPhone(rs.getString("phone"));
                customer.setEmail(rs.getString("email"));
                customer.setAddress(rs.getString("address"));
                customerList.add(customer);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return customerList;
    }

    // Add Customer
    @Override
    public boolean checkCustomer(String email) {
        Connection conn = null;
        CallableStatement callSt = null;

        try {
            conn = ConnectionDB.openConnection();
            conn.setAutoCommit(false);

            callSt = conn.prepareCall("{call check_customer_email_is_exist(?,?)}");
            callSt.setString(1, email);
            callSt.registerOutParameter(2, Types.VARCHAR);
            callSt.execute();
            conn.commit();

            int cnt = callSt.getInt(2);
            if (cnt > 0) {
                return true;
            }

        } catch (Exception e) {
            try {
                conn.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return false;
    }

    @Override
    public boolean addCustomer(Customer customer) {
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call add_customer(?,?,?,?)}");
            callSt.setString(1, customer.getName());
            callSt.setString(2, customer.getPhone());
            callSt.setString(3, customer.getEmail());
            callSt.setString(4, customer.getAddress());
            callSt.executeUpdate();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }

        return false;
    }

    // Update Customer

    @Override
    public Customer findCustomerById(int customerId) {
        Connection conn = null;
        CallableStatement callSt = null;

        Customer customer = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call find_customer_by_id(?)}");
            callSt.setInt(1, customerId);
            ResultSet rs = callSt.executeQuery();
            if (rs.next()) {
                customer = new Customer();
                customer.setId(rs.getInt("id"));
                customer.setName(rs.getString("name"));
                customer.setPhone(rs.getString("phone"));
                customer.setEmail(rs.getString("email"));
                customer.setAddress(rs.getString("address"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return customer;
    }

    @Override
    public boolean updateCustomer(Customer customer) {
        Connection conn = null;
        CallableStatement callSt = null;

        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call update_customer(?,?,?,?,?)}");
            callSt.setInt(1, customer.getId());
            callSt.setString(2, customer.getName());
            callSt.setString(3, customer.getPhone());
            callSt.setString(4, customer.getEmail());
            callSt.setString(5, customer.getAddress());
            callSt.executeUpdate();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return false;
    }

    // Delete Customer
    @Override
    public boolean deleteCustomer(Customer customer) {
        Connection conn = null;
        CallableStatement callSt = null;

        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call delete_customer(?)}");
            callSt.setInt(1, customer.getId());
            callSt.executeUpdate();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return false;
    }


}
