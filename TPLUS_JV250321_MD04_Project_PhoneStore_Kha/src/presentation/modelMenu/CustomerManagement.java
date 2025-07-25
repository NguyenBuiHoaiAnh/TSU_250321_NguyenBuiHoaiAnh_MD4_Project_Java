package presentation.modelMenu;

import business.customer.CustomerBusiness;
import business.customer.imp.CustomerBusinessImp;
import business.product.ProductBusiness;
import business.product.imp.ProductBusinessImp;
import model.Customer;
import validate.Validator;

import java.util.Scanner;

public class CustomerManagement {

    private CustomerBusiness customerBusiness;

    public CustomerManagement() {
        customerBusiness = new CustomerBusinessImp();
    }

    public void CustomerManagementMenu(Scanner scanner) {
        boolean isExit = true;
        do {
            System.out.println("=========== QUẢN LÍ KHÁCH HÀNG ==========");
            System.out.println("1. Hiển thị danh sách khách hàng");
            System.out.println("2. Thêm khách hàng mới");
            System.out.println("3. Cập nhật thông tin khách hàng");
            System.out.println("4. Xóa khách hàng theo ID");
            System.out.println("5. Quay lại menu chính");
            System.out.println("=========================================");
            int choice = Validator.inputValidInteger(scanner, "Lựa chọn của bạn: ");

            switch (choice) {
                case 1:
                    displayCustomers();
                    break;
                case 2:
                    inputAddCustomer(scanner);
                    break;
                case 3:
                    inputUpdateCustomer(scanner);
                    break;
                case 4:
                    inputDeleteCustomer(scanner);
                    break;
                case 5:
                    isExit = false; // while(false)
                    break;
            }
        } while (isExit); // while(true)
    }

    // 1. Display Customer
    public void displayCustomers() {
        customerBusiness.findAll().forEach(System.out::println);
    }

    // 2.1. Add Customer
    public void inputAddCustomer(Scanner scanner) {
        Customer customer = new Customer();

        customer.setName(inputCustomerName(scanner));

        customer.setPhone(inputCustomerPhone(scanner));

        customer.setEmail(inputCustomerEmail(scanner));

        customer.setAddress(inputCustomerAddress(scanner));

        boolean result = customerBusiness.addCustomer(customer);

        if (result) {
            System.out.println("Thêm mới thành công");
        } else {
            System.out.println("Có lỗi trong lúc thêm mới");
        }
    }

    // 2.2. Input Validate
    public String inputCustomerEmail(Scanner scanner) {
        System.out.println("Nhập vào email:");
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

    public String inputCustomerName(Scanner scanner) {
        return Validator.inputNotEmptyData(scanner, "Nhập vào tên:");
    }

    public String inputCustomerPhone(Scanner scanner) {
        return Validator.inputNotEmptyData(scanner, "Nhập vào số điện thoại:");
    }

    public String inputCustomerAddress(Scanner scanner) {
        return Validator.inputNotEmptyData(scanner,"Nhập vào địa chỉ:");
    }

    // --------------------------------------------------------------

    // 3. Update Customer
    public void inputUpdateCustomer(Scanner scanner) {
        System.out.println("Nhập vào ID cần cập nhật:");
        int updateId = Integer.parseInt(scanner.nextLine());

        Customer customerById = customerBusiness.findCustomerById(updateId);

        if (customerById != null) {
            boolean isExit = true;
            do {
                System.out.println("============ Update Customer Menu ================");
                System.out.println("1. Cập nhật tên");
                System.out.println("2. Cập nhật số điện thoại");
                System.out.println("3. Cập nhật email");
                System.out.println("4. Cập nhật địa chỉ");
                System.out.println("5. Thoát");
                int choice = Validator.inputValidInteger(scanner, "Lựa chọn của bạn: ");

                switch (choice) {
                    case 1:
                        customerById.setName(inputCustomerName(scanner));;
                        break;
                    case 2:
                        customerById.setPhone(inputCustomerPhone(scanner));
                        break;
                    case 3:
                        customerById.setEmail(inputCustomerEmail(scanner));
                        break;
                    case 4:
                        customerById.setAddress(inputCustomerAddress(scanner));
                        break;
                    case 5:
                        isExit = false;
                        break;
                    default:
                        System.out.println("Vui lòng chọn từ 1-5");
                }

            } while (isExit);

            boolean result = customerBusiness.updateCustomer(customerById);
            if (result) {
                System.out.println("Cập nhật thành công");
            } else {
                System.out.println("Lỗi cập nhật");
            }

        } else {
            System.out.println("Không tồn tại khách hàng");
        }

    }

    // 4. Delete Customer
    public void inputDeleteCustomer(Scanner scanner) {
        System.out.println("Nhập vào ID cần xóa:");
        int deleteId = Integer.parseInt(scanner.nextLine());

        Customer customerById = customerBusiness.findCustomerById(deleteId);

        if (customerById != null) {
            boolean result = customerBusiness.deleteCustomer(customerById);
            if (result) {
                System.out.println("Xóa thành công");
            } else {
                System.out.println("Có lỗi khi xóa");
            }
        } else {
            System.out.println("Không tồn tại ID");
        }
    }

}
