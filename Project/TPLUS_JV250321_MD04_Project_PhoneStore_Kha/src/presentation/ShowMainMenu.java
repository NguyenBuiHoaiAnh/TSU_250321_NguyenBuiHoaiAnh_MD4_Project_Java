package presentation;

import java.util.Scanner;

public class ShowMainMenu {
    public boolean MainMenu(Scanner scanner) {

        ProductManagement productManagement = new ProductManagement();
        do {
            System.out.println("========= MAIN MENU =========");
            System.out.println("1. Quản lí sản phẩm điện thoại");
            System.out.println("2. Quản lí khách hàng");
            System.out.println("3. Quản lí hóa đơn");
            System.out.println("4. Thống kê doanh thu");
            System.out.println("5. Đăng xuất");
            System.out.print("Nhập lựa chọn: ");
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    productManagement.ProductManagement(scanner);
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    return true;
                default:
                    System.out.println("Vui lòng chọn từ 1 đến 5");
            }

        } while (true);
    }
}
