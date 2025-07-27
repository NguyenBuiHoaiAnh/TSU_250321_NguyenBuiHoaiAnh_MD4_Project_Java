package model;

import validate.Validator;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class StatiticInvoice {
    private LocalDate date;

    private int month;
    private int year;
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


    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    // ---------------------------------------------------


    public String displayTotalAmountByMonth() {
        return String.format("Date: %s/%s, TotalAmount: %.2f%n",
                this.month, this.year, this.totalAmount);
    }

    public String displayTotalAmountByYear() {
        return String.format("Date: %s, TotalAmount: %.2f%n",
                this.year, this.totalAmount);
    }

    @Override
    public String toString() {
        return String.format("Date: %s, TotalAmount: %.2f",
                this.date.format(Validator.dateFormatter), this.totalAmount);
    }
}
