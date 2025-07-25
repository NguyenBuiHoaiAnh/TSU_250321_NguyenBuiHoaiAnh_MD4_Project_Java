package validate;

import javax.swing.text.DateFormatter;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Validator {
    public static boolean isEmpty(String data) {
//        if (data != null && !data.trim().isEmpty()) {
//            return false;
//        }
//        return true;
        return data == null || data.trim().isEmpty();
    }

    public static boolean isInteger(String data) {
        try {
            Integer.parseInt(data);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static int inputValidInteger(Scanner scanner, String message) {
        System.out.println(message);
        do {
            String input = scanner.nextLine();
            if (isInteger(input)) {
                return Integer.parseInt(input);
            }
            System.out.println("Vui lòng nhập số nguyên");
        } while (true);
    }

    public static String inputNotEmptyData(Scanner scanner, String message) {
        System.out.println(message);
        do {
            String input = scanner.nextLine();
            if (isEmpty(input)) {
                System.out.println("Chưa nhập dữ liệu, xin hãy nhập dữ liệu:");
            } else {
                return input;
            }
        } while (true);
    }

    public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    public static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

}
