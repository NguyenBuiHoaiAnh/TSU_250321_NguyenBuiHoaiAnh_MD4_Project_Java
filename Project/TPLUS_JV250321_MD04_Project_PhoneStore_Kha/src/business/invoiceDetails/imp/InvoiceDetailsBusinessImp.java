package business.invoiceDetails.imp;

import business.invoiceDetails.InvoiceDetailsBusiness;
import dao.invoiceDetails.InvoiceDetailsDAO;
import dao.invoiceDetails.imp.InvoiceDetailsDAOImp;
import model.InvoiceDetails;
import model.StatiticInvoice;

import java.time.LocalDate;
import java.util.List;

public class InvoiceDetailsBusinessImp implements InvoiceDetailsBusiness {

    private InvoiceDetailsDAO invoiceDetailsDAO;

    public InvoiceDetailsBusinessImp() {
        invoiceDetailsDAO = new InvoiceDetailsDAOImp();
    }

    // Add Invoice Details
    @Override
    public boolean checkProductId(int id) {
        return invoiceDetailsDAO.checkProductId(id);
    }

    @Override
    public boolean addInvoiceDetails(List<InvoiceDetails> invoiceDetailsList) {
        return invoiceDetailsDAO.addInvoiceDetails(invoiceDetailsList);
    }

    // Statitic Total Amount By Day
    @Override
    public List<StatiticInvoice> statiticTotalAmountByDate(LocalDate date) {
        return invoiceDetailsDAO.statiticTotalAmountByDate(date);
    }
}
