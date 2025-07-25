package dao.invoiceDetails.imp;

import dao.invoice.InvoiceDAO;
import dao.invoiceDetails.InvoiceDetailsDAO;
import model.Invoice;
import model.InvoiceDetails;
import model.StatiticInvoice;
import util.ConnectionDB;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class InvoiceDetailsDAOImp implements InvoiceDetailsDAO {

    // Add Batch Invoice Details

    @Override
    public boolean checkProductId(int id) {
        Connection conn = null;
        CallableStatement callSt = null;

        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call check_product_id_is_exist(?)}");
            callSt.setInt(1, id);
            callSt.registerOutParameter(2, Types.INTEGER);
            callSt.execute();

            int cnt = callSt.getInt(2);
            if (cnt > 0) {
                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return false;
    }

    @Override
    public boolean addInvoiceDetails(List<InvoiceDetails> invoiceDetailsList) {
        Connection conn = null;
        CallableStatement callSt = null;

        try {
            conn = ConnectionDB.openConnection();
            conn.setAutoCommit(false);

            callSt = conn.prepareCall("{call add_invoice_details(?,?,?,?)}");

            for (InvoiceDetails invoiceDetails : invoiceDetailsList) {
                callSt.setInt(1, invoiceDetails.getInvoiceId());
                callSt.setInt(2, invoiceDetails.getProductId());
                callSt.setInt(3, invoiceDetails.getQuantity());
                callSt.setDouble(4, invoiceDetails.getUnitPrice());
                callSt.executeUpdate();
            }
            conn.commit();
            return true;

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

    // Statitic Total Amount By Day

    @Override
    public List<StatiticInvoice> statiticTotalAmountByDate(LocalDate date) {
        Connection conn = null;
        CallableStatement callSt = null;
        List<StatiticInvoice> statiticInvoiceList = null;

        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call statitic_total_amount_by_day(?)}");
            callSt.setDate(1, Date.valueOf(date));
            ResultSet rs = callSt.executeQuery();

            statiticInvoiceList = new ArrayList<>();
            while (rs.next()) {
                StatiticInvoice statiticInvoice = new StatiticInvoice();
                statiticInvoice.setDate(rs.getDate("date(created_at)").toLocalDate());
                statiticInvoice.setTotalAmount(rs.getDouble("sum(total_amount)"));
                statiticInvoiceList.add(statiticInvoice);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return statiticInvoiceList;
    }

    // Statitic Total Amount By Month
    @Override
    public List<StatiticInvoice> statiticTotalAmountByMonth(int month) {
        Connection conn = null;
        CallableStatement callSt = null;

        try {
            conn = ConnectionDB.openConnection();


        } catch (Exception e){
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return null;
    }


}
