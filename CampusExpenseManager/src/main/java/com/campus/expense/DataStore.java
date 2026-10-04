package com.campus.expense;

import java.io.*;
import java.util.*;

public class DataStore {
    private static final String DATA_DIR = "data";
    private static final String EXPENSE_FILE = DATA_DIR + "/expenses.csv";
    private static final String BUDGET_FILE = DATA_DIR + "/budgets.csv";

    public DataStore() {
        new File(DATA_DIR).mkdirs();
        createIfMissing(EXPENSE_FILE, "id,date,category,description,amount");
        createIfMissing(BUDGET_FILE, "month,limit");
    }

    private void createIfMissing(String path, String header) {
        File file = new File(path);
        if (!file.exists()) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                writer.write(header);
                writer.newLine();
            } catch (IOException e) {
                System.out.println("Warning: Could not initialize " + path);
            }
        }
    }

    public List<Expense> loadExpenses() {
        List<Expense> result = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(EXPENSE_FILE))) {
            String line;
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] p = line.split(",", -1);
                if (p.length >= 5) {
                    try {
                        result.add(new Expense(Integer.parseInt(p[0]), p[1],
                                ExpenseCategory.valueOf(p[2]), p[3],
                                Double.parseDouble(p[4])));
                    } catch (RuntimeException ignored) {}
                }
            }
        } catch (IOException e) {
            System.out.println("Could not read expense data: " + e.getMessage());
        }
        return result;
    }

    public List<Budget> loadBudgets() {
        List<Budget> result = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(BUDGET_FILE))) {
            String line;
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] p = line.split(",", -1);
                if (p.length == 2) {
                    try { result.add(new Budget(p[0], Double.parseDouble(p[1]))); }
                    catch (RuntimeException ignored) {}
                }
            }
        } catch (IOException e) {
            System.out.println("Could not read budget data: " + e.getMessage());
        }
        return result;
    }

    public void saveExpenses(List<Expense> expenses) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(EXPENSE_FILE))) {
            writer.write("id,date,category,description,amount");
            writer.newLine();
            for (Expense e : expenses) {
                writer.write(e.toCsv());
                writer.newLine();
            }
        }
    }

    public void saveBudgets(List<Budget> budgets) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(BUDGET_FILE))) {
            writer.write("month,limit");
            writer.newLine();
            for (Budget b : budgets) {
                writer.write(b.toCsv());
                writer.newLine();
            }
        }
    }
}
