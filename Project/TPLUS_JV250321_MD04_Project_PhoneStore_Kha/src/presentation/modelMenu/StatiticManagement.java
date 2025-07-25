package presentation.modelMenu;

import business.invoiceDetails.InvoiceDetailsBusiness;
import business.invoiceDetails.imp.InvoiceDetailsBusinessImp;
import model.InvoiceDetails;
import validate.Validator;

import java.time.LocalDate;
import java.util.Scanner;

public class StatiticManagement {

    private InvoiceDetailsBusiness invoiceDetailsBusiness;

    public StatiticManagement(){
        invoiceDetailsBusiness = new InvoiceDetailsBusinessImp();
    }

    public void StatiticManagementMenu(Scanner scanner) {

        boolean isExit = true;

        do {
            System.out.println("================ THỐNG KÊ HÓA ĐƠN ===============");
            System.out.println("1. Doanh thu theo ngày");
            System.out.println("2. Doanh thu theo tháng");
            System.out.println("3. Doanh thu theo năm");
            System.out.println("4. Thoát");
            int choice = Validator.inputValidInteger(scanner, "Lựa chọn của bạn");

            switch (choice) {
                case 1:
                    inputTotalAmountByDate(scanner);
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

    public void inputTotalAmountByDate(Scanner scanner) {
        do {
            try {
                LocalDate date = LocalDate.parse(inputDateTime(scanner),Validator.dateFormatter);
                if (date.isBefore(LocalDate.now())) {
                    invoiceDetailsBusiness.statiticTotalAmountByDate(date).forEach(System.out::println);
                } else {
                    System.out.println("Ngày hiện tại không phù hợp");
                }
                break;
            } catch (Exception e) {
//                System.out.println("Lỗi không đúng định dạng(dd/MM/yyyy)");
                e.printStackTrace();
            }
        } while (true);

//    public void inputDateTimeValidate(Scanner scanner, Invoice invoice) {
//        do {
//            try {
//                invoice.setDateTime(LocalDateTime.parse(inputDateTime(scanner), Validator.formatter));
//                break;
//            } catch (Exception e) {
//                System.out.println("Lỗi không đúng định dạng(dd/MM/yyyy HH:mm:ss)");
//            }
//        } while (true);
    }

    public String inputDateTime(Scanner scanner) {
        return Validator.inputNotEmptyData(scanner, "Nhập vào ngày cần thống kê doanh thu:");
    }
}
