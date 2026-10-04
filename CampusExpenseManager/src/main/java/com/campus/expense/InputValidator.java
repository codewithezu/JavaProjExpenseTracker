package com.campus.expense;

public final class InputValidator {
    private InputValidator() {}

    public static void validateDate(String date) throws AppException {
        if (date == null || !date.matches("\\d{4}-\\d{2}-\\d{2}")) {
            throw new AppException("Date must use YYYY-MM-DD format.");
        }
    }

    public static void validateMonth(String month) throws AppException {
        if (month == null || !month.matches("\\d{4}-\\d{2}")) {
            throw new AppException("Month must use YYYY-MM format.");
        }
    }

    public static void validateAmount(double amount) throws AppException {
        if (amount <= 0) throw new AppException("Amount must be greater than zero.");
    }

    public static void validateText(String text, String field) throws AppException {
        if (text == null || text.trim().isEmpty()) {
            throw new AppException(field + " cannot be empty.");
        }
    }
}
