package business.invoice.imp;

import business.invoice.InvoiceBusiness;
import dao.invoice.InvoiceDAO;
import dao.invoice.imp.InvoiceDAOImp;
import model.Invoice;

import java.util.List;

public class InvoiceBusinessImp implements InvoiceBusiness {
    private InvoiceDAO invoiceDAO;

    public InvoiceBusinessImp(){
        invoiceDAO = new InvoiceDAOImp();
    }

    public List<Invoice> findAll(){
        return invoiceDAO.findAll();
    }
}
