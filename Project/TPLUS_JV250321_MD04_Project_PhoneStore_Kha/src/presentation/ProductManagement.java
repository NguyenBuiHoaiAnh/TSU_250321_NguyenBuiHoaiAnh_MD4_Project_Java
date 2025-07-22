package presentation;

import business.product.ProductBusiness;
import business.product.imp.ProductBusinessImp;
import model.Product;
import validate.Validator;

import java.time.LocalDate;
import java.util.Scanner;

public class ProductManagement {

    private ProductBusiness productBusiness1;

    public ProductManagement() {
        productBusiness1 = new ProductBusinessImp();
    }

    public void ProductManagement(Scanner scanner) {

        boolean isExit = false;
        do {
            System.out.println("=========== QUẢN LÍ SẢN PHẨM =============");
            System.out.println("1. Hiển thị danh sách sản phẩm");
            System.out.println("2. Thêm sản phẩm mới");
            System.out.println("3. Cập nhật thông tin sản phẩm");
            System.out.println("4. Xóa sản phẩm theo ID");
            System.out.println("5. Tìm kiếm theo Brand");
            System.out.println("6. Tìm kiếm theo khoảng giá");
            System.out.println("7. Tìm kiếm theo tồn kho");
            System.out.println("8. Quay lại Menu");
            System.out.print("Nhập lựa chọn: ");
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    displayProduct();
                    break;
                case 2:
                    inputAddProduct(scanner);
                    break;
                case 3:
                    inputUpdateProduct(scanner);
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 7:
                    break;
                case 8:
                    isExit = true;
                    break;
                default:
                    System.out.println("Vui lòng chọn từ 1 đến 8");
            }
        } while (!isExit);
    }

    // 1. Display
    public void displayProduct() {
        productBusiness1.findAll().forEach(System.out::println);
    }

    // 2.1. Add Product
    public void inputAddProduct(Scanner scanner) {
        Product product = new Product();

        product.setProductName(inputProductName(scanner));

        System.out.println("Nhập vào hãng sản phẩm:");
        product.setBrand(scanner.nextLine());
        System.out.println("Nhập vào giá sản phẩm:");
        product.setPrice(Double.parseDouble(scanner.nextLine()));
        System.out.println("Nhập vào số lượng:");
        product.setStock(Integer.parseInt(scanner.nextLine()));

        boolean result = productBusiness1.addProduct(product);

        if (result) {
            System.out.println("Thêm mới thành công");
        } else {
            System.out.println("Có lỗi trong quá trình thêm mới");
        }
    }

    // 2.2. Input Validate
    public String inputProductName(Scanner scanner) {

        System.out.println("Nhập vào tên sản phẩm:");
        do {
            String name = scanner.nextLine();
            if (Validator.isEmpty(name)) {
                System.out.println("Không để trống tên");
            } else {
                if (productBusiness1.checkProduct(name)) {
                    System.out.println("Tên đã tồn tại vui lòng nhập lại");
                } else {
                    return name;
                }
            }
        } while (true);
    }

    // 3. Update Product
    public void inputUpdateProduct(Scanner scanner) {
        System.out.println("Nhập vào ID cần cập nhật:");
        int id = Integer.parseInt(scanner.nextLine());

        Product productById = productBusiness1.findProductById(id);

        if (productById != null) {
            boolean isExit = false;
            do {
                System.out.println("1. Cập nhật tên");
                System.out.println("2. Cập nhật hãng");
                System.out.println("3. Cập nhật giá");
                System.out.println("4. Cập nhật số lượng");
                System.out.println("5. Thoát");
                System.out.println("Lựa chọn của bạn:");
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        productById.setProductName(inputProductName(scanner));
                        break;
                    case 2:
                        System.out.println("Nhập vào hãng:");
                        productById.setBrand(scanner.nextLine());
                        break;
                    case 3:
                        System.out.println("Nhập vào giá:");
                        productById.setPrice(Integer.parseInt(scanner.nextLine()));
                        break;
                    case 4:
                        System.out.println("Nhập vào số lượng:");
                        productById.setStock(Integer.parseInt(scanner.nextLine()));
                        break;
                    case 5:
                        isExit = true;
                        break;
                    default:
                        System.err.println("Vui lòng chọn từ 1-5");
                }
            } while (isExit);

            // Cập nhật
            boolean result = productBusiness1.updateProduct(productById);
            if (result) {
                System.out.println("Cập nhật thành công");
            } else {
                System.out.println("Có lỗi trong quá trình cập nhật");
            }

        } else {
            System.out.println("ID không tồn tại");
        }

    }

    // 4. Delete Product
    public void inputDeleteProduct(Scanner scanner) {}

// ------------------------------------------------------------


}
