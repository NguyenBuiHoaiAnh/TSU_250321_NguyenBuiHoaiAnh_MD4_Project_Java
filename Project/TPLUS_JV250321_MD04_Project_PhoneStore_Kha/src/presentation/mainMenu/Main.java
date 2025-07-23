package presentation.mainMenu;

import validate.Validator;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AdminLogin adminLogin = new AdminLogin();

        boolean isExit = false;
        do {
            System.out.println("========= HỆ THỐNG QUẢN LÍ CỬA HÀNG =========");
            System.out.println("1. Đăng nhập Admin");
            System.out.println("2. Thoát");
            System.out.println("=============================================");

            int choice = Validator.inputValidInteger(scanner, "Nhập lựa chọn: ");

            switch (choice) {
                case 1:
                    boolean backToMenu = adminLogin.Adminlogin(scanner);
                    if (!backToMenu) {
                        isExit = true;
                    }
                    break;
                case 2:
                    isExit = true;
                    break;
                default:
                    System.out.println("Vui lòng chọn từ 1 hoặc 2");
            }
        } while (!isExit);
    }
}