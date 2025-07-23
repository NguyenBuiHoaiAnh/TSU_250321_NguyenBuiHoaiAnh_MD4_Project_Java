package dao.invoice.imp;

import dao.invoice.InvoiceDAO;
import model.Invoice;
import util.ConnectionDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
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
                invoice.setId(rs.getInt("id"));
                invoice.setCustomerId(rs.getInt("customer_id"));
                invoice.setDateTime(LocalDateTime.parse(rs.getString("created_at")));
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
}
