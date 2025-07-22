package dao.product.imp;

import dao.product.ProductDAO;
import model.Product;
import util.ConnectionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAOImp implements ProductDAO {

    // Find All
    @Override
    public List<Product> findAll() {
        Connection conn = null;
        CallableStatement callSt = null;
        List<Product> productList = null;

        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call find_all_product()}");
            ResultSet rs = callSt.executeQuery();

            productList = new ArrayList<>();
            while (rs.next()) {
                Product product = new Product();
                product.setId(rs.getInt("id"));
                product.setProductName(rs.getString("name"));
                product.setBrand(rs.getString("brand"));
                product.setPrice(rs.getDouble("price"));
                product.setStock(rs.getInt("stock"));
                productList.add(product);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return productList;
    }

    // Add Product
    @Override
    public boolean checkProduct(String name) {
        Connection conn = null;
        CallableStatement callSt = null;

        try {
            conn = ConnectionDB.openConnection();
            conn.setAutoCommit(false);

            callSt = conn.prepareCall("{call check_product_is_exist(?,?)}");
            callSt.setString(1, name);
            callSt.registerOutParameter(2, Types.INTEGER);
            callSt.execute();

            int cnt = callSt.getInt(2);
            if (cnt > 0) {
                return true;
            }
            conn.commit();

        } catch (Exception e) {
            try {
                conn.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return false;
    }

    @Override
    public boolean addProduct(Product product) {
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            conn.setAutoCommit(false);

            callSt = conn.prepareCall("{call add_product(?,?,?,?)}");
            callSt.setString(1, product.getProductName());
            callSt.setString(2, product.getBrand());
            callSt.setDouble(3, product.getPrice());
            callSt.setInt(4, product.getStock());
            callSt.executeUpdate();
            conn.commit();
            return true;

        } catch (Exception e) {
            try {
                conn.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return false;
    }

    // Update Product
    @Override
    public Product findProductById(int productId) {
        Connection conn = null;
        CallableStatement callSt = null;

        Product product = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call find_product_by_id(?)}");
            callSt.setInt(1, productId);
            ResultSet rs = callSt.executeQuery();
            if (rs.next()) {
                product = new Product();
                product.setId(rs.getInt("id"));
                product.setProductName(rs.getString("name"));
                product.setBrand(rs.getString("brand"));
                product.setPrice(rs.getDouble("price"));
                product.setStock(rs.getInt("stock"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return product;
    }

    @Override
    public boolean updateProduct(Product product) {
        Connection conn = null;
        CallableStatement callSt = null;

        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call update_product(?,?,?,?,?)}");
            callSt.setInt(1, product.getId());
            callSt.setString(2, product.getProductName());
            callSt.setString(3, product.getBrand());
            callSt.setDouble(4, product.getPrice());
            callSt.setInt(5, product.getStock());
            callSt.executeUpdate();

            return true;
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return false;
    }

    // Delete Product
    @Override
    public boolean deleteProduct(int productId) {
        Connection conn = null;
        CallableStatement callSt = null;

        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call delete_product(?)}");
            callSt.setInt(1, productId);
            callSt.executeUpdate();

            return true;
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }

        return false;
    }

    // Search By Brand
    @Override
    public Product findProductByBrand(String Brand) {
        Connection conn = null;
        CallableStatement callSt = null;
        Product product = null;

        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call find_product_by_brand(?)}");


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return product;
    }

    // Search By
}
