package dao.invoice;

import model.Invoice;

import java.util.List;

public interface InvoiceDAO {
    // Find All
    List<Invoice> findAll();
}
