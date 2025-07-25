package dao.invoiceDetails;

import model.Invoice;
import model.InvoiceDetails;
import model.StatiticInvoice;

import java.time.LocalDate;
import java.util.List;

public interface InvoiceDetailsDAO {

    // Add Batch Invoice Details
    boolean checkProductId(int id);

    boolean addInvoiceDetails(List<InvoiceDetails> invoiceDetailsList);

    // Statitic Total Amount By Day
    List<StatiticInvoice> statiticTotalAmountByDate(LocalDate date);

    // Statitic Total Amount By Month
    List<StatiticInvoice> statiticTotalAmountByMonth(int month);
}
