package business.product;

import model.Product;

import java.util.List;

public interface ProductBusiness {

    // Find All
    List<Product> findAll();

    // Add Product
    boolean checkProduct(String name);

    boolean addProduct(Product product);

    // Update Product
    Product findProductById(int id);

    boolean updateProduct(Product product);

    // Delete Product
    boolean deleteProduct(int id);

    // Search By Brand
    List<Product> findProductByBrand(String Brand);

    // Find By Price Range
    List<Product> searchProductByPrice(double priceIn, double priceOut);

    // Find By Stock Available
    List<Product> searchProductByStock(double priceIn, double priceOut);

}
