package model;

import java.time.LocalDateTime;

public class Invoice {
    private int id;
    private int customerId;
    private LocalDateTime dateTime;
    private double totalAmount;

    public Invoice() {
    }

    public Invoice(int id, int customerId, LocalDateTime dateTime, double totalAmount) {
        this.id = id;
        this.customerId = customerId;
        this.dateTime = dateTime;
        this.totalAmount = totalAmount;
    }

    // ---------------------- Getter and Setter ----------------------------


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    // -----------------------------------------------------------------

    @Override
    public String toString() {
        return String.format("ID: %d, Customer Id: %d, Created At: %s, Total Amount: %.2f",
                this.id, this.customerId, this.dateTime, this.totalAmount);
    }
}
