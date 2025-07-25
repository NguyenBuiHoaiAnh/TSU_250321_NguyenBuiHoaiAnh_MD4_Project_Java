package presentation.modelMenu;

import business.customer.CustomerBusiness;
import business.customer.imp.CustomerBusinessImp;
import business.invoice.InvoiceBusiness;
import business.invoice.imp.InvoiceBusinessImp;
import business.invoiceDetails.InvoiceDetailsBusiness;
import business.invoiceDetails.imp.InvoiceDetailsBusinessImp;
import business.product.ProductBusiness;
import business.product.imp.ProductBusinessImp;
import model.Invoice;
import model.InvoiceDetails;
import validate.Validator;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InvoiceManagement {
    private InvoiceBusiness invoiceBusiness;
    private CustomerBusiness customerBusiness;
    private ProductBusiness productBusiness;
    private InvoiceDetailsBusiness invoiceDetailsBusiness;

    public InvoiceManagement() {
        invoiceBusiness = new InvoiceBusinessImp();
        customerBusiness = new CustomerBusinessImp();
        productBusiness = new ProductBusinessImp();
        invoiceDetailsBusiness = new InvoiceDetailsBusinessImp();
    }

    public void InvoiceManagementMenu(Scanner scanner) {

        boolean isExit = true;
        do {
            System.out.println("============== QUẢN LÍ HÓA ĐƠN ==============");
            System.out.println("1. Hiển thị danh sách hóa đơn");
            System.out.println("2. Thêm mới hóa đơn");
            System.out.println("3. Tìm kiếm hóa đơn");
            System.out.println("4. Quay lại menu chính");
            int choice = Validator.inputValidInteger(scanner, "Lựa chọn của bạn: ");
            switch (choice) {
                case 1:
                    displayInvoices();
                    break;
                case 2:
                    inputAddInvoice(scanner);
                    break;
                case 3:
                    inputSearchInvoice(scanner);
                    break;
                case 4:
                    isExit = false;
                    break;
            }
        } while (isExit);
    }

    // 1. Find All

    public void displayInvoices() {
        invoiceBusiness.findAll().forEach(System.out::println);
    }

    // 2.1. Add Invoice

    public void inputAddInvoice(Scanner scanner) {
        Invoice invoice = new Invoice();

        invoice.setCustomerId(inputCustomerId(scanner));

//        inputDateTimeValidate(scanner, invoice);
//        invoice.setTotalAmount(Double.parseDouble(inputTotalAmount(scanner)));

        boolean result = invoiceBusiness.addInvoice(invoice);
        if (result) {
            System.out.println("Thêm mới thành công");
            // Thêm invoice detail ở đây
            // invoice.getId
            // tự nhập productId,quantity,unitPrice
            inputBatchInvoicesDetails(scanner, invoice);

        } else {
            System.out.println("Có lỗi trong quá trình thêm mới");
        }
    }

    // 2.2. Input Validate
    // DateTime

//    public String inputDateTime(Scanner scanner) {
//        return Validator.inputNotEmptyData(scanner, "Nhập vào ngày tạo:");
//    }
//
//    public void inputDateTimeValidate(Scanner scanner, Invoice invoice) {
//        do {
//            try {
//                invoice.setDateTime(LocalDateTime.parse(inputDateTime(scanner), Validator.formatter));
//                break;
//            } catch (Exception e) {
//                System.out.println("Lỗi không đúng định dạng(dd/MM/yyyy HH:mm:ss)");
//            }
//        } while (true);
//    }

    // -----------------------------

//    public String inputTotalAmount(Scanner scanner) {
//        return Validator.inputNotEmptyData(scanner, "Nhập vào tổng tiền:");
//    }


    public int inputCustomerId(Scanner scanner) {
        customerBusiness.findAll().forEach(System.out::println);

        System.out.println("Nhập vào ID khách hàng (invoiceCustomerId):");
        do {
            String customerId = scanner.nextLine();
            if (Validator.isEmpty(customerId)) {
                System.out.println("Không để trống ID");
            } else {
                try {
                    if (invoiceBusiness.checkCustomerID(Integer.parseInt(customerId))) {
                        return Integer.parseInt(customerId);
                    }
                    System.out.println("Không tồn tại ID");

                } catch (Exception e) {
                    System.out.println("Không đúng định dạng số nguyên");
                }
            }
        } while (true);
    }


// -----------------------------------------------------------------------------


// 3. Search Invoice

    public void inputSearchInvoice(Scanner scanner) {
        boolean isExit = true;
        do {
            System.out.println("=========== Search Menu =============");
            System.out.println("1. Tìm kiếm hóa đơn theo tên khách hàng");
            System.out.println("2. Tìm kiếm hóa đơn theo ngày tháng năm (dd/MM/yyyy)");
            System.out.println("3. Quay lại menu hóa đơn");
            int choice = Validator.inputValidInteger(scanner, "Chọn:\n");
            switch (choice) {
                case 1:
                    inputSearchInvoiceByName(scanner);
                    break;
                case 2:
                    inputSearchInvoiceByDate(scanner);
                    break;
                case 3:
                    isExit = false;
                    break;
            }

        } while (isExit);
    }

    public void inputSearchInvoiceByName(Scanner scanner) {
        System.out.println("Nhập tên khách hàng cần tìm:");
        do {
            String search = scanner.nextLine();
            if (Validator.isEmpty(search)) {
                System.out.println("Không để trống tên");
            } else {
                invoiceBusiness.findInvoiceByCustomerName(search).forEach(System.out::println);
                break;
            }
        } while (true);
    }

    public void inputSearchInvoiceByDate(Scanner scanner) {
        System.out.println("Nhập ngày tháng năm cần tìm:");
        LocalDate searchDate = null;

        do {
            try {
                String search = scanner.nextLine();
                if (Validator.isEmpty(search)) {
                    System.out.println("Không để trống dữ liệu");
                }
                searchDate = LocalDate.parse(search, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                break;

            } catch (Exception e) {
                System.out.println("Phải đúng định dạng");
            }
        } while (true);


        List<Invoice> invoiceList = invoiceBusiness.findInvoiceByDate(searchDate);

        if (!invoiceList.isEmpty()) {
            invoiceList.forEach(System.out::println);
        } else {
            System.out.println("Không có dữ liệu phù hợp ngày tháng năm");
        }
    }

// -----------------------------------------------------------------------------


// 4. Add Batch Invoice Details

    public void inputBatchInvoicesDetails(Scanner scanner, Invoice invoice) {
        List<InvoiceDetails> invoiceDetailsList = new ArrayList<>();
        System.out.println("Nhập vào số lượng chi tiết hóa đơn cần thêm mới:");
        int cntInDe = Integer.parseInt(scanner.nextLine());

        System.out.println("============= Invoices Details ==============");
        for (int i = 0; i < cntInDe; i++) {
            InvoiceDetails invoiceDetails = new InvoiceDetails();

            invoiceDetails.setInvoiceId(invoice.getId());
            invoiceDetails.setProductId(inputProductIdInDe(scanner));

            // Cần modify thêm
            invoiceDetails.setQuantity(Validator.inputValidInteger(scanner, "Nhập số lượng sản phẩm (quantity):"));

//            System.out.println("Nhập số lượng sản phẩm (quantity):");
//            invoiceDetails.setQuantity(Integer.parseInt(scanner.nextLine()));

            System.out.println("Nhập giá sản phẩm:");
            invoiceDetails.setUnitPrice(Double.parseDouble(scanner.nextLine()));

            invoiceDetailsList.add(invoiceDetails);
        }

        boolean result = invoiceDetailsBusiness.addInvoiceDetails(invoiceDetailsList);
        if (result) {
            System.out.println("Thêm mới thành công");
        } else {
            System.out.println("Có lỗi khi thêm mới");
        }
    }

    public int inputProductIdInDe(Scanner scanner) {
        productBusiness.findAll().forEach(System.out::println);

        System.out.println("Nhập vào mã sản phẩm");
        do {
            String choice = scanner.nextLine();
            if (Validator.isEmpty(choice)) {
                System.out.println("Không để trống dữ liệu");
            } else {
                int result = Integer.parseInt(choice);
                if (invoiceDetailsBusiness.checkProductId(result)) {
                    return result;
                } else {
                    System.out.println("Không tồn tại dữ liệu");
                }
            }
        } while (true);

    }

// ----------------------------------------------------------------------------------------


}
