package business.product.imp;

import business.product.ProductBusiness;
import dao.product.ProductDAO;
import dao.product.imp.ProductDAOImp;
import model.Product;

import java.util.List;

public class ProductBusinessImp implements ProductBusiness {

    private ProductDAO productDAO;

    public ProductBusinessImp(){
        productDAO = new ProductDAOImp();
    }

    // Find All
    @Override
    public List<Product> findAll() {
        return productDAO.findAll();
    }


    // Add Product
    @Override
    public boolean checkProduct(String name) {
        return productDAO.checkProduct(name);
    }

    @Override
    public boolean addProduct(Product product) {
        return productDAO.addProduct(product);
    }


    // Update Product
    @Override
    public Product findProductById(int id) {
        return productDAO.findProductById(id);
    }

    @Override
    public boolean updateProduct(Product product) {
        return productDAO.updateProduct(product);
    }

    // Delete Product
    @Override
    public boolean deleteProduct(int id) {
        return productDAO.deleteProduct(id);
    }

    // Search By Brand
    @Override
    public List<Product> findProductByBrand(String Brand) {
        return productDAO.findProductByBrand(Brand);
    }

    // Find By Price Range
    @Override
    public List<Product> searchProductByPrice(double priceIn, double priceOut) {
        return productDAO.searchProductByPrice(priceIn, priceOut);
    }

    // Find By Stock Avaiable
    @Override
    public List<Product> searchProductByStock(double priceIn, double priceOut) {
        return productDAO.searchProductByStock(priceIn, priceOut);
    }

}
