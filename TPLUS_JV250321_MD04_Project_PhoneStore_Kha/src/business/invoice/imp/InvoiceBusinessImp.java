package business.invoice.imp;

import business.invoice.InvoiceBusiness;
import dao.invoice.InvoiceDAO;
import dao.invoice.imp.InvoiceDAOImp;
import model.Invoice;

import java.time.LocalDate;
import java.util.List;

public class InvoiceBusinessImp implements InvoiceBusiness {
    private InvoiceDAO invoiceDAO;

    public InvoiceBusinessImp(){
        invoiceDAO = new InvoiceDAOImp();
    }

    // Find All
    public List<Invoice> findAll(){
        return invoiceDAO.findAll();
    }

    // Add Invoice

    @Override
    public boolean checkCustomerID(int customerId) {
        return invoiceDAO.checkCustomerID(customerId);
    }

    @Override
    public boolean addInvoice(Invoice invoice) {
        return invoiceDAO.addInvoice(invoice);
    }

    // Search By Customer Name

    @Override
    public List<Invoice> findInvoiceByCustomerName(String customerName) {
        return invoiceDAO.findInvoiceByCustomerName(customerName);
    }

    // Search By Customer Date
    @Override
    public List<Invoice> findInvoiceByDate(LocalDate date) {
        return invoiceDAO.findInvoiceByDate(date);
    }
}
