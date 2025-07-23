package business.invoice;

import model.Invoice;

import java.util.List;

public interface InvoiceBusiness {
    List<Invoice> findAll();
}
