package validate;

import java.util.Scanner;

public class Validator {
    public static boolean isEmpty(String data) {
        if (data != null && !data.trim().isEmpty()) {
            return false;
        }
        return true;
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
        System.out.print(message);
        do {
            String input = scanner.nextLine();
            if (isInteger(input)) {
                return Integer.parseInt(input);
            }
            System.out.println("Vui lòng nhập số nguyên");
        } while (true);
    }

}
