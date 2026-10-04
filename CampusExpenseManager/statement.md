# Project Statement

## Problem Statement
Students often make frequent small purchases but do not maintain a structured record of their spending. This makes it difficult to understand where money is going, compare spending with a budget, and identify high-spending categories.

## Scope
The Campus Expense & Budget Manager is a command-line Java application that allows a student to maintain personal expense records, manage monthly budgets, search transactions, and generate spending summaries.

The project focuses on local data storage using CSV files and does not require an external database or internet connection.

## Target Users
- College students
- Students living in hostels or rented accommodation
- Students who want a simple offline expense tracker

## High-Level Features
- Expense CRUD operations
- Monthly budget management
- Category-based search
- Month filtering
- Spending analytics
- CSV persistence
- Input validation and error handling

## Major Functional Modules
1. **Expense Management** — add, view, update, delete and search expenses.
2. **Budget Management** — create/update monthly budgets and compare them with spending.
3. **Reports & Analytics** — calculate total spending and category-wise/month-wise summaries.

## Non-Functional Requirements
1. **Usability:** menu-driven terminal interface with clear prompts.
2. **Reliability:** invalid input is handled without terminating the application.
3. **Maintainability:** responsibilities are separated into model, service, storage, and utility classes.
4. **Resource Efficiency:** the application uses in-memory collections and lightweight CSV storage.
5. **Portability:** runs anywhere with Java 17+ and a terminal.
6. **Data Persistence:** records remain available after restarting the application.
