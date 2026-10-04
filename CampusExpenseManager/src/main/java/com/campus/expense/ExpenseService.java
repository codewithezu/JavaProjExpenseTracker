package com.campus.expense;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class ExpenseService {
    private final List<Expense> expenses;
    private final DataStore store;

    public ExpenseService(DataStore store) {
        this.store = store;
        this.expenses = new ArrayList<>(store.loadExpenses());
    }

    public List<Expense> getAll() {
        return Collections.unmodifiableList(expenses);
    }

    public void add(String date, ExpenseCategory category, String description, double amount)
            throws AppException {
        InputValidator.validateDate(date);
        InputValidator.validateText(description, "Description");
        InputValidator.validateAmount(amount);

        int id = expenses.stream().mapToInt(Expense::getId).max().orElse(0) + 1;
        expenses.add(new Expense(id, date, category, description.trim(), amount));
        save();
    }

    public Expense findById(int id) throws AppException {
        return expenses.stream().filter(e -> e.getId() == id).findFirst()
                .orElseThrow(() -> new AppException("Expense not found."));
    }

    public void update(int id, String date, ExpenseCategory category,
                       String description, double amount) throws AppException {
        Expense e = findById(id);
        InputValidator.validateDate(date);
        InputValidator.validateText(description, "Description");
        InputValidator.validateAmount(amount);

        e.setDate(date);
        e.setCategory(category);
        e.setDescription(description.trim());
        e.setAmount(amount);
        save();
    }

    public void delete(int id) throws AppException {
        Expense e = findById(id);
        expenses.remove(e);
        save();
    }

    public List<Expense> search(String keyword) {
        String k = keyword.toLowerCase();
        return expenses.stream()
                .filter(e -> e.getDescription().toLowerCase().contains(k)
                        || e.getCategory().name().toLowerCase().contains(k))
                .collect(Collectors.toList());
    }

    public List<Expense> byMonth(String month) {
        return expenses.stream()
                .filter(e -> e.getDate().startsWith(month))
                .collect(Collectors.toList());
    }

    private void save() throws AppException {
        try { store.saveExpenses(expenses); }
        catch (IOException e) { throw new AppException("Could not save expenses: " + e.getMessage()); }
    }
}
