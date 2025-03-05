import java.time.LocalDate;
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
                    System.out.print("Enter amount: ");
                    double amount = scanner.nextDouble();
                    scanner.nextLine();

                    System.out.print("Enter category: ");
                    String category = scanner.nextLine();
                    System.out.print("Enter description: ");
                    String description = scanner.nextLine();

                    System.out.print("Enter date (YYYY-MM-DD): ");
                    String date = scanner.nextLine();

                    manager.addExpense(new Expense(amount, category, description, date));
                    System.out.println("Expense added successfully.");
                    break;

                case 2:
                    manager.viewExpenses();
                    break;

                case 3:
                    manager.viewExpenses();
                    System.out.print("Enter expense number to remove: ");
                    int removeIndex = scanner.nextInt() - 1;
                    manager.removeExpense(removeIndex);
                    break;

                case 4:
                    manager.viewExpenses();
                    System.out.print("Enter expense number to update: ");
                    int updateIndex = scanner.nextInt() - 1;
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
                    manager.updateExpense(updateIndex, newAmount, newCategory, newDescription, newDate);
                    break;

                case 5:
                    System.out.println("Total expenses for today: $" + manager.getTotalExpensesForDay(LocalDate.now().toString()));
                    break;

                case 6:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid option.");
            }
        }
    }
}
