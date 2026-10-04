package com.campus.expense;

public class Budget {
    private String month;
    private double limit;

    public Budget(String month, double limit) {
        this.month = month;
        this.limit = limit;
    }

    public String getMonth() { return month; }
    public double getLimit() { return limit; }

    public void setLimit(double limit) { this.limit = limit; }

    public String toCsv() {
        return month + "," + String.format("%.2f", limit);
    }
}
