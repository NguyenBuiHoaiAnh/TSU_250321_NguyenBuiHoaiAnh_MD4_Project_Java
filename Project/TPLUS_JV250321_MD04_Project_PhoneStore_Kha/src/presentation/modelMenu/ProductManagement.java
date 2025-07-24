package presentation.modelMenu;

import business.product.ProductBusiness;
import business.product.imp.ProductBusinessImp;
import model.Product;
import validate.Validator;

import java.util.List;
import java.util.Objects;
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
            System.out.println("==========================================");
            int choice = Validator.inputValidInteger(scanner, "Nhập lựa chọn: ");

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
                    inputDeleteProduct(scanner);
                    break;
                case 5:
                    inputSearchProductByBrand(scanner);
                    break;
                case 6:
                    inputSearchProductByPrice(scanner);
                    break;
                case 7:
                    inputSearchProductByStock(scanner);
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

        product.setBrand(inputProductBrand(scanner));

        product.setPrice(Double.parseDouble(inputProductPrice(scanner)));

        product.setStock(Integer.parseInt(inputProductStock(scanner)));

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

    public String inputProductBrand(Scanner scanner) {
        return Validator.inputNotEmptyData(scanner,"Nhập vào hãng sản phẩm:");
    }

    public String inputProductPrice(Scanner scanner) {
        return Validator.inputNotEmptyData(scanner,"Nhập vào giá sản phẩm:");
    }

    public String inputProductStock(Scanner scanner) {
        return Validator.inputNotEmptyData(scanner,"Nhập vào số lượng:");
    }

    // -----------------------------------------------------------

    // 3. Update Product
    public void inputUpdateProduct(Scanner scanner) {
        System.out.println("Nhập vào ID cần cập nhật:");
        int id = Integer.parseInt(scanner.nextLine());

        Product productById = productBusiness1.findProductById(id);

        if (productById != null) {
            boolean isExit = false;
            do {
                System.out.println("=========== Update Product Menu ==============");
                System.out.println("1. Cập nhật tên");
                System.out.println("2. Cập nhật hãng");
                System.out.println("3. Cập nhật giá");
                System.out.println("4. Cập nhật số lượng");
                System.out.println("5. Thoát");
                System.out.println("======================================");
                System.out.println("Lựa chọn của bạn:");
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        productById.setProductName(inputProductName(scanner));
                        break;
                    case 2:
                        productById.setBrand(inputProductBrand(scanner));
                        break;
                    case 3:
                        productById.setPrice(Double.parseDouble(inputProductPrice(scanner)));
                        break;
                    case 4:
                        productById.setStock(Integer.parseInt(inputProductStock(scanner)));
                        break;
                    case 5:
                        isExit = true;
                        break;
                    default:
                        System.out.println("Vui lòng chọn từ 1-5");
                }

                // Cập nhật
                boolean result = productBusiness1.updateProduct(productById);
                if (result) {
                    System.out.println("Cập nhật thành công");
                } else {
                    System.out.println("Có lỗi trong quá trình cập nhật");
                }
            } while (isExit);

        } else {
            System.out.println("ID không tồn tại");
        }
    }

    // 4. Delete Product
    public void inputDeleteProduct(Scanner scanner) {
        System.out.println("Nhập ID cần xóa:");
        int deleteId = Integer.parseInt(scanner.nextLine());

        Product productById = productBusiness1.findProductById(deleteId);

        if (productById != null) {
            System.out.println("Bạn có chắc muốn xóa sản phẩm hay không? Y/N");
            String answer = scanner.nextLine();
            if (answer.equalsIgnoreCase("Y")) {
                boolean result = productBusiness1.deleteProduct(deleteId);
                if (result) {
                    System.out.println("Xóa hoặc cập nhật thành công");
                } else {
                    System.out.println("Cố lỗi trong quá trình xóa");
                }
            }
        } else {
            System.out.println("Không tồn tại sản phẩm");
        }
    }

    // 5. Search By Brand
    public void inputSearchProductByBrand(Scanner scanner) {
        System.out.println("Nhập hãng cần tìm:");
        String brand = scanner.nextLine();

        List<Product> listproduct = productBusiness1.findProductByBrand(brand);
        if (!listproduct.isEmpty()) {

//            for (Product product : listproduct) {
//                System.out.println(product);
//            }

            listproduct.forEach(System.out::println);
        } else {
            System.out.println("Không tìm thấy sản phẩm");
        }
    }

    // 6. Search By Price Range
    public void inputSearchProductByPrice(Scanner scanner) {
        System.out.println("Nhập khoảng giá đầu:");
        double priceIn = Double.parseDouble(scanner.nextLine());
        System.out.println("Nhập khoảng giá cuối:");
        double priceOut = Double.parseDouble(scanner.nextLine());

        List<Product> listproduct = productBusiness1.searchProductByPrice(priceIn, priceOut);

        if (!listproduct.isEmpty()) {
//            for (Product product : listproduct) {
//                if (product != null) {
//                    System.out.println(product);
//                }
//            }

            listproduct.stream()
                    .filter(Objects::nonNull)
                    .forEach(System.out::println);
        } else {
            System.out.println("Không tìm thấy sản phẩm");
        }
    }

    // 7. Search By Stock Available
    public void inputSearchProductByStock(Scanner scanner) {
        System.out.println("Nhập mốc tồn kho đầu:");
        double stockIn = Double.parseDouble(scanner.nextLine());
        System.out.println("Nhập mốc tồn kho cuối:");
        double stockOut = Double.parseDouble(scanner.nextLine());
        List<Product> listproduct = productBusiness1.searchProductByStock(stockIn, stockOut);

        if (!listproduct.isEmpty()) {
//            for (Product product : listproduct) {
//                if (product != null) {
//                    System.out.println(product);
//                }
//            }

            listproduct.stream()
                    .filter(Objects::nonNull)
                    .forEach(System.out::println);
        } else {
            System.out.println("Không có sản phẩm nào");
        }
    }

// ------------------------------------------------------------

}
