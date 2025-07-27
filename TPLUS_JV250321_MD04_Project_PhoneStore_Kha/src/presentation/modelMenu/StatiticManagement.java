package presentation.modelMenu;

import business.invoiceDetails.InvoiceDetailsBusiness;
import business.invoiceDetails.imp.InvoiceDetailsBusinessImp;
import model.InvoiceDetails;
import model.StatiticInvoice;
import validate.Validator;

import java.time.LocalDate;
import java.time.Year;
import java.util.List;
import java.util.Scanner;

public class StatiticManagement {

    private InvoiceDetailsBusiness invoiceDetailsBusiness;

    public StatiticManagement() {
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
                    inputTotalAmountByMonth(scanner);
                    break;
                case 3:
                    inputTotalAmountByYear(scanner);
                    break;
                case 4:
                    isExit = false;
                    break;
            }
        } while (isExit);
    }

    // Thống kê theo ngày
    public void inputTotalAmountByDate(Scanner scanner) {
        do {
            try {
                LocalDate date = LocalDate.parse(inputDateTimeByDate(scanner), Validator.dateFormatter);
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
    }

    public String inputDateTimeByDate(Scanner scanner) {
        return Validator.inputNotEmptyData(scanner, "Nhập vào ngày tháng năm(dd/MM/YYYY) cần thống kê doanh thu:");
    }

    // -------------------------------------------------------------------------------

    // Thống kê theo tháng
    public void inputTotalAmountByMonth(Scanner scanner) {
        do {
            try {
                int month = Integer.parseInt(inputNotEmptyDateTimeByMonth(scanner));

                if (month < 1 || month > 12) {
                    System.out.println("Tháng hiện tại không phù hợp");
                } else {
                    int year = Integer.parseInt(inputNotEmptyDateTimeByYear(scanner));

                    if (year > LocalDate.now().getYear()) {
                        System.out.println("Năm hiện tại không phù hợp");
                    } else {
                        List<StatiticInvoice> statiticInvoiceList = invoiceDetailsBusiness.statiticTotalAmountByMonth(month, year);

                        statiticInvoiceList
                                .forEach(statiticInvoice
                                        -> System.out.println(statiticInvoice
                                        .displayTotalAmountByMonth()));
                        break;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } while (true);
    }

    public String inputNotEmptyDateTimeByMonth(Scanner scanner) {
        return Validator.inputNotEmptyData(scanner, "Nhập vào tháng cần thống kê doanh thu:");
    }

    public String inputNotEmptyDateTimeByYear(Scanner scanner) {
        return Validator.inputNotEmptyData(scanner, "Nhập vào năm cần thống kê doanh thu:");
    }

    // -------------------------------------------------------------------------------

    // Thống kê theo năm
    public void inputTotalAmountByYear(Scanner scanner) {
        do {
            try {
                int year = Integer.parseInt(inputNotEmptyDateTimeByYear(scanner));

                if (year < LocalDate.now().getYear()) {

                    List<StatiticInvoice> statiticInvoiceList = invoiceDetailsBusiness.statiticTotalAmountByYear(year);

                    statiticInvoiceList
                            .forEach(statiticInvoice ->
                            System.out.println(statiticInvoice.displayTotalAmountByYear()));

                } else {
                    System.out.println("Năm không hợp lệ");
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        } while (true);
    }

}
