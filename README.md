Daily Expense Tracker

Project Overview

Daily Expense Tracker is a Java-based console application that allows users to efficiently manage their daily expenses. It enables users to add, view, update, and delete expenses while also providing summary reports on daily, weekly, and monthly spending.

How I Created This Project

This project was built using Java with a focus on - - -Object-Oriented Programming (OOP)
principles. The program consists of multiple classes, including:

- Expense: Represents an expense with attributes like amount, category, description, and date.
- ExpenseManager: Handles the operations such as adding, removing, updating, and calculating total expenses.
- Main: Contains the main function that interacts with the user via a menu-driven system.

Additionally, expenses are stored in a file (expenses.txt), allowing data persistence between program runs.

- Functionalities

1. Add Expense: Users can enter details like amount, category, description, and date to record an expense.
2. View Expenses: Displays all recorded expenses.
3. Remove Expense: Allows users to delete an expense by selecting it from the list.
4. Update Expense: Users can modify details of an existing expense.
5. View Summary:
   - Daily Expense: Calculates total expenses for the current day.
   - Weekly Expense: Computes expenses for the last 7 days.
   - Monthly Expense: Summarizes expenses for the current month.
6. Data Persistence: Expenses are stored in a text file (`expenses.txt`) to ensure data is retained even after closing the program.
7. Error Handling: Includes input validation to prevent invalid data entry.

Technologies Used

- Programming Language: Java
- File Handling: Used to store and retrieve expense data.
- Date & Time API: Utilized Java's `LocalDate` and `DateTimeFormatter` for date operations.
- Collections Framework: Used `ArrayList` to manage expenses dynamically.

How It Works

1. The user is presented with a menu to choose an operation.
2. Based on the selected option, the program performs the respective task.
3. Expenses are stored in memory and written to `expenses.txt`.
4. When viewing summaries, the program calculates total expenses for different time frames.

GitHub Repository

[GitHub Repository](https://github.com/nazrulhaq12/Daily-Expense-Tracker)

How to Run

1. Clone the repository:
   ```sh
   git clone https://github.com/nazrulhaq12/Daily-Expense-Tracker.git
   ```
2. Navigate to the project folder:
   ```sh
   cd DailyExpenseTracker
   ```
3. Compile the Java files:
   ```sh
   javac *.java
   ```
4. Run the program:
   ```sh
   java Main
   ```

Future Improvements

- Implement a graphical user interface (GUI).
- Add database support instead of file-based storage.
- Introduce expense categorization with charts and graphs.

---

This Daily Expense Tracker is a simple yet powerful tool for managing daily expenses. Happy tracking!

