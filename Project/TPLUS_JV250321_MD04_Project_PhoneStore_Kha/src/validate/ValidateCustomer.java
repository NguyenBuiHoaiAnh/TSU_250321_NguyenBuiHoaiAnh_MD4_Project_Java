package validate;

import business.customer.CustomerBusiness;
import business.customer.imp.CustomerBusinessImp;

import java.util.Scanner;

public class ValidateCustomer {

    private CustomerBusiness customerBusiness;

    public ValidateCustomer() {
        customerBusiness = new CustomerBusinessImp();
    }

    public String validateCustomerEmail(Scanner scanner) {
        System.out.println("Nhập vào email:");
        String regex = "\\w{3,10}@\\w.{2,}";

        do {
            String email = scanner.nextLine();
            if (!Validator.isEmpty(email)) {
                System.out.println("Email không được để trống");
            } else {
                if (customerBusiness.checkCustomer(email)) {
                    System.out.println("Email không tồn tại");
                } else {
                    return email;
                }
            }
        } while (true);
    }
}
