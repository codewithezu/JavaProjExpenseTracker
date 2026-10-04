# Campus Expense & Budget Manager

A Java command-line application for students to record expenses, manage monthly budgets, search/filter transactions, and generate spending reports.

## Features
1. Add, view, update, and delete expenses (CRUD).
2. Set and view monthly budgets.
3. Search expenses by category or keyword.
4. Filter expenses by month.
5. Generate monthly spending summaries and category-wise analytics.
6. Persist data using CSV files.
7. Input validation and custom exception handling.

## Technologies
- Java 17+
- Java Collections Framework (`ArrayList`, `HashMap`)
- Java I/O (`BufferedReader`, `BufferedWriter`, `FileReader`, `FileWriter`)
- Exception handling
- Object-oriented programming
- Git/GitHub

## Project Structure
```text
CampusExpenseManager/
├── data/
│   ├── expenses.csv
│   └── budgets.csv
├── src/main/java/com/campus/expense/
│   ├── Main.java
│   ├── Expense.java
│   ├── Budget.java
│   ├── ExpenseCategory.java
│   ├── DataStore.java
│   ├── ExpenseService.java
│   ├── BudgetService.java
│   ├── ReportService.java
│   ├── InputValidator.java
│   └── AppException.java
├── statement.md
└── README.md
```

## Requirements
Install JDK 17 or newer and verify:
```bash
java -version
javac -version
```

## Run from terminal

From the repository root:

### Windows PowerShell
```powershell
mkdir out -ErrorAction SilentlyContinue
javac -d out src/main/java/com/campus/expense/*.java
java -cp out com.campus.expense.Main
```

### Linux/macOS
```bash
mkdir -p out
javac -d out src/main/java/com/campus/expense/*.java
java -cp out com.campus.expense.Main
```

The application automatically creates the required CSV files inside `data/`.

## Testing
Perform these validation tests:
1. Add a valid expense and verify it appears in View Expenses.
2. Enter an invalid amount such as `-100`; the application must reject it.
3. Update an existing expense and verify the saved value.
4. Delete an expense and verify it is removed.
5. Set a budget and generate a monthly report.
6. Restart the application and verify previously saved data is loaded.

## Example workflow
Start application → Add Expense → Set Budget → View Expenses → Search/Filter → Generate Report → Exit.

## Academic Concepts Demonstrated
- Classes and objects
- Encapsulation
- Enums
- Interfaces through service-oriented design
- `ArrayList` and `HashMap`
- String operations
- File and character-oriented I/O
- Exception handling
- Modular package structure
- Input validation
