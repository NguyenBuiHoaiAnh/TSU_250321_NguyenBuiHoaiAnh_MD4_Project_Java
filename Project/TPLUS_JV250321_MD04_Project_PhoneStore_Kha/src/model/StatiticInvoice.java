package model;

import validate.Validator;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class StatiticInvoice {
    private LocalDate date;
    private double totalAmount;

    public StatiticInvoice() {
    }

    // --------------- Getter And Setter --------------------

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    // ---------------------------------------------------


    @Override
    public String toString() {
        return String.format("Date: %s, TotalAmount: %.2f",
                this.date.format(Validator.dateFormatter), this.totalAmount);
    }
}
