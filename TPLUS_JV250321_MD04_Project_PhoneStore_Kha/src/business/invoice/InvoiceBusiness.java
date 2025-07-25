package business.invoice;

import model.Invoice;

import java.time.LocalDate;
import java.util.List;

public interface InvoiceBusiness {

    // Find All
    List<Invoice> findAll();

    // Add Invoice
    boolean checkCustomerID(int customerId);

    boolean addInvoice(Invoice invoice);

    // Search Invoice By Customer Name
    List<Invoice> findInvoiceByCustomerName(String customerName);

    // Search Invoice By Customer Date
    List<Invoice> findInvoiceByDate(LocalDate date);

}
