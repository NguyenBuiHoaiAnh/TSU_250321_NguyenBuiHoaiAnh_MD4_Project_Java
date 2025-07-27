package business.invoiceDetails;

import model.InvoiceDetails;
import model.StatiticInvoice;

import java.time.LocalDate;
import java.util.List;

public interface InvoiceDetailsBusiness {

    // Add Invoice Details
    boolean checkProductId(int id);

    boolean addInvoiceDetails(List<InvoiceDetails> invoiceDetailsList);

    // Statitic TotalAmount By Day
    List<StatiticInvoice> statiticTotalAmountByDate(LocalDate date);

    // Statitic TotalAmount By Month
    List<StatiticInvoice> statiticTotalAmountByMonth(int month, int year);

    // Statitic TotalAmount By Year
    List<StatiticInvoice> statiticTotalAmountByYear(int year);
}
