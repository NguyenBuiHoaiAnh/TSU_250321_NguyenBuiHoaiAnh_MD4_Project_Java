package presentation.modelMenu;

import business.invoice.InvoiceBusiness;
import business.invoice.imp.InvoiceBusinessImp;
import validate.Validator;

import java.util.Scanner;

public class InvoiceManagement {
    private InvoiceBusiness invoiceBusiness;

    public InvoiceManagement() {
        invoiceBusiness = new InvoiceBusinessImp();
    }

    public void InvoiceManagement(Scanner scanner) {

        boolean isExit = true;
        do {
            System.out.println("============== QUẢN LÍ HÓA ĐƠN ==============");
            System.out.println("1. Hiển thị danh sách hóa đơn");
            System.out.println("2. Thêm mới hóa đơn");
            System.out.println("3. Tìm kiếm hóa đơn");
            System.out.println("4. Quay lại menu chính");
            int choice = Validator.inputValidInteger(scanner,"Lựa chọn của bạn: ");
            switch (choice) {
                case 1:
                    displayInvoices();
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    isExit = false;
                    break;
            }
        } while (isExit);
    }

    public void displayInvoices() {
        invoiceBusiness.findAll().forEach(System.out::println);
    }


}
