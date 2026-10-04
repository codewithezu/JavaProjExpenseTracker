package com.campus.expense;

import java.io.IOException;
import java.util.*;

public class BudgetService {
    private final List<Budget> budgets;
    private final DataStore store;

    public BudgetService(DataStore store) {
        this.store = store;
        this.budgets = new ArrayList<>(store.loadBudgets());
    }

    public void setBudget(String month, double limit) throws AppException {
        InputValidator.validateMonth(month);
        InputValidator.validateAmount(limit);

        for (Budget b : budgets) {
            if (b.getMonth().equals(month)) {
                b.setLimit(limit);
                save();
                return;
            }
        }
        budgets.add(new Budget(month, limit));
        save();
    }

    public Budget getBudget(String month) {
        return budgets.stream()
                .filter(b -> b.getMonth().equals(month))
                .findFirst().orElse(null);
    }

    public List<Budget> getAll() {
        return Collections.unmodifiableList(budgets);
    }

    private void save() throws AppException {
        try { store.saveBudgets(budgets); }
        catch (IOException e) { throw new AppException("Could not save budgets: " + e.getMessage()); }
    }
}
