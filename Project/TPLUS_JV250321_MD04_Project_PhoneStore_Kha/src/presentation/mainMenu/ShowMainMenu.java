package presentation.mainMenu;

import presentation.modelMenu.CustomerManagement;
import presentation.modelMenu.InvoiceManagement;
import presentation.modelMenu.ProductManagement;
import presentation.modelMenu.StatiticManagement;
import validate.Validator;

import java.util.Scanner;

public class ShowMainMenu {
    public boolean MainMenu(Scanner scanner) {

        ProductManagement productManagement = new ProductManagement();
        CustomerManagement customerManagement = new CustomerManagement();
        InvoiceManagement invoiceManagement = new InvoiceManagement();
        StatiticManagement statiticManagement = new StatiticManagement();


        do {
            System.out.println("========= MAIN MENU =========");
            System.out.println("1. Quản lí sản phẩm điện thoại");
            System.out.println("2. Quản lí khách hàng");
            System.out.println("3. Quản lí hóa đơn");
            System.out.println("4. Thống kê doanh thu");
            System.out.println("5. Đăng xuất");
            System.out.println("=============================");

            int choice = Validator.inputValidInteger(scanner,"Nhập lựa chọn: ");

            switch (choice) {
                case 1:
                    productManagement.ProductManagementMenu(scanner);
                    break;
                case 2:
                    customerManagement.CustomerManagementMenu(scanner);
                    break;
                case 3:
                    invoiceManagement.InvoiceManagementMenu(scanner);
                    break;
                case 4:
                    statiticManagement.StatiticManagementMenu(scanner);
                    break;
                case 5:
                    return true;
                default:
                    System.out.println("Vui lòng chọn từ 1 đến 5");
            }

        } while (true);
    }
}
