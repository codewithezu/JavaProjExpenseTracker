package com.campus.expense;

import java.util.*;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static ExpenseService expenseService;
    private static BudgetService budgetService;
    private static final ReportService reportService = new ReportService();

    public static void main(String[] args) {
        DataStore store = new DataStore();
        expenseService = new ExpenseService(store);
        budgetService = new BudgetService(store);

        System.out.println("======================================");
        System.out.println(" CAMPUS EXPENSE & BUDGET MANAGER");
        System.out.println("======================================");

        while (true) {
            printMenu();
            String choice = scanner.nextLine().trim();
            try {
                switch (choice) {
                    case "1" -> addExpense();
                    case "2" -> viewExpenses(expenseService.getAll());
                    case "3" -> updateExpense();
                    case "4" -> deleteExpense();
                    case "5" -> searchExpenses();
                    case "6" -> setBudget();
                    case "7" -> report();
                    case "8" -> viewBudgets();
                    case "0" -> {
                        System.out.println("Thank you for using the application.");
                        return;
                    }
                    default -> System.out.println("Invalid menu choice.");
                }
            } catch (AppException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (RuntimeException e) {
                System.out.println("Error: Please enter valid input.");
            }
        }
    }

    private static void printMenu() {
        System.out.println("\n1. Add Expense");
        System.out.println("2. View All Expenses");
        System.out.println("3. Update Expense");
        System.out.println("4. Delete Expense");
        System.out.println("5. Search Expenses");
        System.out.println("6. Set/Update Monthly Budget");
        System.out.println("7. Generate Monthly Report");
        System.out.println("8. View Budgets");
        System.out.println("0. Exit");
        System.out.print("Choose: ");
    }

    private static void addExpense() throws AppException {
        System.out.println("\n--- Add Expense ---");
        String date = read("Date (YYYY-MM-DD): ");
        ExpenseCategory category = readCategory();
        String description = read("Description: ");
        double amount = readDouble("Amount: ");
        expenseService.add(date, category, description, amount);
        System.out.println("Expense added successfully.");
    }

    private static void viewExpenses(List<Expense> list) {
        System.out.println("\n--- Expenses ---");
        if (list.isEmpty()) {
            System.out.println("No expenses found.");
            return;
        }
        list.forEach(System.out::println);
    }

    private static void updateExpense() throws AppException {
        int id = readInt("Expense ID to update: ");
        Expense old = expenseService.findById(id);
        System.out.println("Current: " + old);
        String date = read("New date (YYYY-MM-DD): ");
        ExpenseCategory category = readCategory();
        String description = read("New description: ");
        double amount = readDouble("New amount: ");
        expenseService.update(id, date, category, description, amount);
        System.out.println("Expense updated successfully.");
    }

    private static void deleteExpense() throws AppException {
        int id = readInt("Expense ID to delete: ");
        expenseService.delete(id);
        System.out.println("Expense deleted successfully.");
    }

    private static void searchExpenses() {
        String keyword = read("Search keyword/category: ");
        viewExpenses(expenseService.search(keyword));
    }

    private static void setBudget() throws AppException {
        String month = read("Month (YYYY-MM): ");
        double limit = readDouble("Budget limit: ");
        budgetService.setBudget(month, limit);
        System.out.println("Budget saved successfully.");
    }

    private static void report() throws AppException {
        String month = read("Month (YYYY-MM): ");
        reportService.printMonthlyReport(month, expenseService, budgetService);
    }

    private static void viewBudgets() {
        System.out.println("\n--- Budgets ---");
        if (budgetService.getAll().isEmpty()) {
            System.out.println("No budgets found.");
            return;
        }
        budgetService.getAll().forEach(b ->
                System.out.printf("%s | ₹%.2f%n", b.getMonth(), b.getLimit()));
    }

    private static ExpenseCategory readCategory() {
        ExpenseCategory[] values = ExpenseCategory.values();
        System.out.println("Categories:");
        for (int i = 0; i < values.length; i++)
            System.out.println((i + 1) + ". " + values[i]);
        int choice = readInt("Choose category: ");
        if (choice < 1 || choice > values.length) throw new IllegalArgumentException();
        return values[choice - 1];
    }

    private static String read(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    private static int readInt(String prompt) {
        return Integer.parseInt(read(prompt));
    }

    private static double readDouble(String prompt) {
        return Double.parseDouble(read(prompt));
    }
}
