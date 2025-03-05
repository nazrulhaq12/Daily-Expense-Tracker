import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ExpenseManager manager = new ExpenseManager();

        while (true) {
            System.out.println("\n1. Add Expense");
            System.out.println("2. View Expenses");
            System.out.println("3. Remove Expense");
            System.out.println("4. Update Expense");
            System.out.println("5. View Summaries");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");

            if (!scanner.hasNextInt()) {
                System.out.println("Invalid input! Please enter a number.");
                scanner.next();
                continue;
            }

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    double amount;
                    while (true) {
                        System.out.print("Enter amount: ");
                        if (scanner.hasNextDouble()) {
                            amount = scanner.nextDouble();
                            scanner.nextLine();
                            if (amount > 0) break;
                            else System.out.println("Amount must be greater than zero.");
                        } else {
                            System.out.println("Invalid input! Please enter a valid number.");
                            scanner.next();
                        }
                    }

                    System.out.print("Enter category: ");
                    String category = scanner.nextLine();
                    System.out.print("Enter description: ");
                    String description = scanner.nextLine();

                    String date;
                    while (true) {
                        System.out.print("Enter date (YYYY-MM-DD): ");
                        date = scanner.nextLine();
                        if (isValidDate(date)) break;
                        else System.out.println("Invalid date format! Please use YYYY-MM-DD.");
                    }

                    manager.addExpense(new Expense(amount, category, description, date));
                    System.out.println("Expense added successfully.");
                    break;

                case 2:
                    manager.viewExpenses();
                    break;

                case 3:
                    if (manager.getExpenseCount() == 0) {
                        System.out.println("No expenses recorded.");
                        break;
                    }
                    manager.viewExpenses();
                    System.out.print("Enter expense number to remove: ");
                    int removeIndex = scanner.nextInt();
                    scanner.nextLine();
                    manager.removeExpense(removeIndex - 1);
                    break;

                case 4:
                    if (manager.getExpenseCount() == 0) {
                        System.out.println("No expenses recorded.");
                        break;
                    }
                    manager.viewExpenses();
                    System.out.print("Enter expense number to update: ");
                    int updateIndex = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Enter new amount: ");
                    double newAmount = scanner.nextDouble();
                    scanner.nextLine();
                    System.out.print("Enter new category: ");
                    String newCategory = scanner.nextLine();
                    System.out.print("Enter new description: ");
                    String newDescription = scanner.nextLine();
                    System.out.print("Enter new date (YYYY-MM-DD): ");
                    String newDate = scanner.nextLine();

                    manager.updateExpense(updateIndex - 1, newAmount, newCategory, newDescription, newDate);
                    break;

                case 5:
                    System.out.println("Total expenses for today: $" + manager.getTotalExpensesForDay(LocalDate.now().toString()));
                    System.out.println("Total expenses for this week: $" + manager.getTotalExpensesForWeek());
                    System.out.println("Total expenses for this month: $" + manager.getTotalExpensesForMonth());
                    break;

                case 6:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }

    private static boolean isValidDate(String date) {
        try {
            LocalDate.parse(date, DateTimeFormatter.ISO_DATE);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
}
