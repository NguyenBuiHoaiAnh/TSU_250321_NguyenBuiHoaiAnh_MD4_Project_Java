package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Invoice {
    private int id;
    private int customerId;
    private LocalDateTime dateTime;
    private double totalAmount;
    private String customerName;

    public Invoice() {
    }

//    public Invoice(int id, int customerId, LocalDateTime dateTime, double totalAmount) {
//        this.id = id;
//        this.customerId = customerId;
//        this.dateTime = dateTime;
//        this.totalAmount = totalAmount;
//    }


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

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    // -----------------------------------------------------------------

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String formattedDate = this.dateTime.format(formatter);

        return String.format("ID: %d, Customer Name: %s, Customer Id: %d," +
                             " Created At: %s, Total Amount: %.2f",
                this.id, this.customerName, this.customerId, formattedDate, this.totalAmount);
    }


}
