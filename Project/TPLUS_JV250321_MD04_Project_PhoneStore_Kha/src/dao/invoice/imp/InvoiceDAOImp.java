package dao.invoice.imp;

import dao.invoice.InvoiceDAO;
import model.Invoice;
import util.ConnectionDB;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class InvoiceDAOImp implements InvoiceDAO {

    // Find All

    @Override
    public List<Invoice> findAll() {
        Connection conn = null;
        CallableStatement callSt = null;
        List<Invoice> invoiceList = null;

        try {
            conn = ConnectionDB.openConnection();

            callSt = conn.prepareCall("{CALL find_all_invoice()}");
            ResultSet rs = callSt.executeQuery();

            invoiceList = new ArrayList<>();
            while (rs.next()) {
                Invoice invoice = new Invoice();
                invoice.setCustomerName(rs.getString("name"));
                invoice.setId(rs.getInt("id"));
                invoice.setCustomerId(rs.getInt("customer_id"));
                invoice.setDateTime(rs.getTimestamp("created_at").toLocalDateTime());
                invoice.setTotalAmount(rs.getDouble("total_amount"));
                invoiceList.add(invoice);
            }

        } catch (Exception e){
            e.printStackTrace();
        } finally{
            ConnectionDB.closeConnection(conn,callSt);
        }
        return invoiceList;
    }

    // Add Invoice

    @Override
    public boolean checkCustomerID(int customerId) {
        Connection conn = null;
        CallableStatement callSt = null;

        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{CALL check_customer_id_is_exist(?,?)}");
            callSt.setInt(1,customerId);
            callSt.registerOutParameter(2, Types.INTEGER);
            callSt.execute();

            int cnt = callSt.getInt(2);
            if (cnt > 0) {
                return true;
            }

        } catch (Exception e){
            e.printStackTrace();
        } finally{
            ConnectionDB.closeConnection(conn,callSt);
        }
        return false;
    }

    @Override
    public boolean addInvoice(Invoice invoice) {
        Connection conn = null;
        CallableStatement callSt = null;

        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{CALL add_invoice(?)}");
            callSt.setInt(1, invoice.getCustomerId());
            callSt.executeUpdate();
            return true;

        } catch (Exception e){
            e.printStackTrace();
        } finally{
            ConnectionDB.closeConnection(conn,callSt);
        }
        return false;
    }

    // Find Invoice By Customer Name

    @Override
    public List<Invoice> findInvoiceByCustomerName(String customerName) {
        Connection conn = null;
        CallableStatement callSt = null;
        List<Invoice> invoiceList = null;

        try {
            conn = ConnectionDB.openConnection();

            callSt = conn.prepareCall("{CALL find_invoice_by_customer_name(?)}");
            callSt.setString(1,customerName);
            ResultSet rs = callSt.executeQuery();
            invoiceList = new ArrayList<>();

            while (rs.next()) {
                Invoice invoice = new Invoice();
                invoice.setCustomerName(rs.getString("name"));
                invoice.setId(rs.getInt("id"));
                invoice.setCustomerId(rs.getInt("customer_id"));
                invoice.setDateTime(rs.getTimestamp("created_at").toLocalDateTime());
                invoice.setTotalAmount(rs.getDouble("total_amount"));
                invoiceList.add(invoice);
            }

        } catch (Exception e){
            e.printStackTrace();
        } finally{
            ConnectionDB.closeConnection(conn,callSt);
        }

        return invoiceList;
    }


    // Find Invoice By Date

    @Override
    public List<Invoice> findInvoiceByDate(LocalDate date) {
        Connection conn = null;
        CallableStatement callSt = null;
        List<Invoice> invoiceList = null;

        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{CALL find_invoice_by_date(?)}");
            callSt.setDate(1,Date.valueOf(date));
            ResultSet rs = callSt.executeQuery();

            invoiceList = new ArrayList<>();
            while (rs.next()) {
                Invoice invoice = new Invoice();
                invoice.setCustomerName(rs.getString("name"));
                invoice.setId(rs.getInt("id"));
                invoice.setCustomerId(rs.getInt("customer_id"));
                invoice.setDateTime(rs.getTimestamp("created_at").toLocalDateTime());
                invoice.setTotalAmount(rs.getDouble("total_amount"));
                invoiceList.add(invoice);
            }

        } catch (Exception e){
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn,callSt);
        }
        return invoiceList;
    }

}
