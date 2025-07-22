package validate;

import model.Admin;

import java.util.Scanner;

public class AdminValidate {
    public static boolean checkAdmin(String inputData) {
        Admin admin = new Admin();
        if (inputData.equals(admin.getUsername())
            && inputData.equals(admin.getPassword())) {
            return true;
        }
        return false;
    }
}
