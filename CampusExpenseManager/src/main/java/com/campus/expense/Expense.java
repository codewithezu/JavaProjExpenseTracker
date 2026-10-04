package com.campus.expense;

public class Expense {
    private int id;
    private String date;
    private ExpenseCategory category;
    private String description;
    private double amount;

    public Expense(int id, String date, ExpenseCategory category, String description, double amount) {
        this.id = id;
        this.date = date;
        this.category = category;
        this.description = description;
        this.amount = amount;
    }

    public int getId() { return id; }
    public String getDate() { return date; }
    public ExpenseCategory getCategory() { return category; }
    public String getDescription() { return description; }
    public double getAmount() { return amount; }

    public void setDate(String date) { this.date = date; }
    public void setCategory(ExpenseCategory category) { this.category = category; }
    public void setDescription(String description) { this.description = description; }
    public void setAmount(double amount) { this.amount = amount; }

    public String toCsv() {
        return id + "," + date + "," + category + "," +
               description.replace(",", " ") + "," + String.format("%.2f", amount);
    }

    @Override
    public String toString() {
        return String.format("#%d | %s | %-13s | %-25s | ₹%.2f",
                id, date, category, description, amount);
    }
}
