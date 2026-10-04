package com.campus.expense;

import java.util.*;

public class ReportService {
    public void printMonthlyReport(String month, ExpenseService expenses, BudgetService budgets)
            throws AppException {
        InputValidator.validateMonth(month);

        List<Expense> list = expenses.byMonth(month);
        double total = list.stream().mapToDouble(Expense::getAmount).sum();

        Map<ExpenseCategory, Double> categoryTotals = new EnumMap<>(ExpenseCategory.class);
        for (Expense e : list) {
            categoryTotals.merge(e.getCategory(), e.getAmount(), Double::sum);
        }

        System.out.println("\n===== MONTHLY REPORT: " + month + " =====");
        System.out.printf("Total expenses: ₹%.2f%n", total);

        Budget budget = budgets.getBudget(month);
        if (budget != null) {
            double remaining = budget.getLimit() - total;
            System.out.printf("Budget: ₹%.2f%n", budget.getLimit());
            System.out.printf("Remaining: ₹%.2f%n", remaining);
            if (remaining < 0) System.out.println("Status: OVER BUDGET");
            else System.out.println("Status: WITHIN BUDGET");
        } else {
            System.out.println("Budget: Not set");
        }

        System.out.println("\nCategory-wise spending:");
        if (categoryTotals.isEmpty()) {
            System.out.println("No expenses recorded.");
        } else {
            categoryTotals.forEach((category, amount) ->
                    System.out.printf("- %-15s ₹%.2f%n", category, amount));
        }
        System.out.println("==============================\n");
    }
}
