package presentation;

import business.admin.AdminBusiness;
import business.admin.imp.AdminBusinessImp;
import model.Admin;
import validate.Validator;

import java.util.Scanner;

public class AdminLogin {

    private AdminBusiness adminBusiness;

    public AdminLogin() {
        adminBusiness = new AdminBusinessImp();
    }

    public boolean Adminlogin(Scanner scanner) {
        ShowMainMenu showMainMenu = new ShowMainMenu();
        do {
            System.out.println("======= ĐĂNG NHẬP QUẢN TRỊ =======");
            System.out.print("Tên đăng nhập: ");
            String username = scanner.nextLine();

            System.out.print("Mật khẩu: ");
            String password = scanner.nextLine();

            // Validate
            if (!Validator.isEmpty(username) && !Validator.isEmpty(password)) {
                Admin admin = adminBusiness.getAdminPermission(username, password);
                if (admin != null) {
                    System.out.println("Đăng nhập thành công");

                    //Gọi MainMenu và xử lý kết quả
                    return showMainMenu.MainMenu(scanner);

//                    if (!isLogout) {
//                        System.out.println("Tạm biệt!");
//                        break; // Thoát khỏi vòng đăng nhập
//                    }

                } else {
                    System.err.println("Sai tài khoản hoặc mật khẩu");
                }
            } else {
                System.err.println("Không để trống mật khẩu hoặc password");
            }
        } while (true);
    }


}
