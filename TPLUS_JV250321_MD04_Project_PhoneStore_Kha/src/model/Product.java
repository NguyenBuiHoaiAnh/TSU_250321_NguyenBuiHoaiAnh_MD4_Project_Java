package model;

public class Product {
    private int id;
    private String productName;
    private String brand;
    private double price;
    private int stock;

    public Product() {
    }

    public Product(int id, String productName, String brand, double price, int stock) {
        this.id = id;
        this.productName = productName;
        this.brand = brand;
        this.price = price;
        this.stock = stock;
    }

    // ---------------- Getter and Setter ----------------------------

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    // --------------------------------------------------------------


    @Override
    public String toString() {
        return String.format("ID: %d, Name: %s, Brand: %s, Price: %.2f, Stock: %d",
                this.id, this.productName, this.brand, this.price, this.stock);
    }
}
