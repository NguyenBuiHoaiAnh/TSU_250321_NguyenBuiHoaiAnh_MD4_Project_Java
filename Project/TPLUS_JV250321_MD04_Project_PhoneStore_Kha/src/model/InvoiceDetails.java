package model;

public class InvoiceDetails {
    private int id;
    private int invoiceId;
    private int productId;
    private int quantity;
    private double unitPrice;

    public InvoiceDetails(){
    }


    // -------------- Getter And Setter -------------------

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(int invoiceId) {
        this.invoiceId = invoiceId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    // ---------------------------------------------------


    @Override
    public String toString() {
        return String.format("ID: %d, InvoiceId: %d, ProductId: %d" +
                             " Quantity: %d, UnitPrice: %.2f",
                this.id, this.invoiceId, this.productId,
                this.quantity, this.unitPrice);
    }
}
