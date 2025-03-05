import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ExpenseManager {
    private List<Expense> expenses;
    private static final String FILE_NAME = "expenses.txt";

    public ExpenseManager() {
        expenses = new ArrayList<>();
        loadExpenses();
    }

    public void addExpense(Expense expense) {
        expenses.add(expense);
        saveExpenses();
    }

    public void viewExpenses() {
        if (expenses.isEmpty()) {
            System.out.println("No expenses recorded.");
            return;
        }
        System.out.println("\n----- Expense List -----");
        for (int i = 0; i < expenses.size(); i++) {
            System.out.println((i + 1) + ". " + expenses.get(i));
        }
    }

    public void removeExpense(int index) {
        if (index >= 0 && index < expenses.size()) {
            expenses.remove(index);
            saveExpenses();
            System.out.println("Expense removed successfully.");
        } else {
            System.out.println("Invalid expense number!");
        }
    }

    public void updateExpense(int index, double newAmount, String newCategory, String newDescription, String newDate) {
        if (index >= 0 && index < expenses.size()) {
            Expense expense = expenses.get(index);
            expense.setAmount(newAmount);
            expense.setCategory(newCategory);
            expense.setDescription(newDescription);
            expense.setDate(newDate);
            saveExpenses();
            System.out.println("Expense updated successfully.");
        } else {
            System.out.println("Invalid expense number!");
        }
    }

    public double getTotalExpensesForDay(String date) {
        return expenses.stream()
                .filter(expense -> expense.getDate().equals(date))
                .mapToDouble(Expense::getAmount)
                .sum();
    }

    public double getTotalExpensesForWeek() {
        LocalDate today = LocalDate.now();
        LocalDate startOfWeek = today.minusDays(today.getDayOfWeek().getValue() - 1);
        return expenses.stream()
                .filter(expense -> {
                    LocalDate expenseDate = LocalDate.parse(expense.getDate(), DateTimeFormatter.ISO_DATE);
                    return !expenseDate.isBefore(startOfWeek) && !expenseDate.isAfter(today);
                })
                .mapToDouble(Expense::getAmount)
                .sum();
    }

    public double getTotalExpensesForMonth() {
        LocalDate today = LocalDate.now();
        return expenses.stream()
                .filter(expense -> {
                    LocalDate expenseDate = LocalDate.parse(expense.getDate(), DateTimeFormatter.ISO_DATE);
                    return expenseDate.getMonth() == today.getMonth() && expenseDate.getYear() == today.getYear();
                })
                .mapToDouble(Expense::getAmount)
                .sum();
    }

    private void saveExpenses() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Expense expense : expenses) {
                writer.write(expense.getAmount() + "," + expense.getCategory() + "," +
                        expense.getDescription() + "," + expense.getDate());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving expenses: " + e.getMessage());
        }
    }

    private void loadExpenses() {
        expenses.clear();
        File file = new File(FILE_NAME);
        if (!file.exists()) return;

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    double amount = Double.parseDouble(parts[0]);
                    String category = parts[1];
                    String description = parts[2];
                    String date = parts[3];
                    expenses.add(new Expense(amount, category, description, date));
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading expenses: " + e.getMessage());
        }
    }

    public int getExpenseCount() {
        return expenses.size();
    }
}
