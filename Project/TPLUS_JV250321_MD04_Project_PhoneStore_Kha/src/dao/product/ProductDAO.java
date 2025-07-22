package dao.product;

import model.Product;

import java.util.List;

public interface ProductDAO {

    // Find All
    List<Product> findAll();

    // Add Product
    boolean checkProduct(String name);

    boolean addProduct(Product product);

    // Update Product
    Product findProductById(int productId);

    boolean updateProduct(Product product);

    // Delete Product
    boolean deleteProduct(int productId);
}
