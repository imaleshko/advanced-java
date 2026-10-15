package org.maleshko;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Main {
    static void main() {
        Tracker tracker = new Tracker();
        ReportGenerator reportGenerator = new ReportGenerator(tracker);
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Облік фінансів ---");
            System.out.println("1 - Додати дохід");
            System.out.println("2 - Додати витрату");
            System.out.println("3 - Показати поточний баланс");
            System.out.println("4 - Загальний звіт (Доходи / Витрати)");
            System.out.println("5 - Показати транзакції за певну дату");
            System.out.println("6 - Звіт за категорією (Доходи)");
            System.out.println("7 - Звіт за категорією (Витрати)");
            System.out.println("8 - Вийти");
            System.out.print("Виберіть варіант: ");

            String choice = scanner.nextLine();

            try {
                switch (choice) {
                    case "1" -> processTransactionInput(scanner, tracker, true);
                    case "2" -> processTransactionInput(scanner, tracker, false);
                    case "3" -> System.out.println("Поточний баланс: " + tracker.getBalance());
                    case "4" -> {
                        System.out.println("Загальний дохід: " + reportGenerator.totalIncome());
                        System.out.println("Загальні витрати: " + reportGenerator.totalExpense());
                    }
                    case "5" -> {
                        System.out.print("Введіть дату (РРРР-ММ-ДД): ");
                        LocalDate dateToSearch = LocalDate.parse(scanner.nextLine().trim());
                        List<Transaction> dailyTransactions = reportGenerator.getTransactionsByDate(dateToSearch);

                        if (dailyTransactions.isEmpty()) {
                            System.out.println("За вказану дату транзакцій не знайдено");
                        } else {
                            System.out.println("Транзакції за " + dateToSearch + ":");
                            for (Transaction t : dailyTransactions) {
                                String type = t.isIncome() ? "+ Дохід" : "- Витрата";
                                System.out.printf("[%s] %s: %s (Категорія: %s)%n",
                                        type, t.amount(), t.description(), t.category());
                            }
                        }
                    }
                    case "6" -> {
                        System.out.print("Введіть назву категорії: ");
                        String category = scanner.nextLine().trim();
                        System.out.println("Доходи за категорією '" + category + "': " + reportGenerator.getIncomeByCategory(category));
                    }
                    case "7" -> {
                        System.out.print("Введіть назву категорії: ");
                        String category = scanner.nextLine().trim();
                        System.out.println("Витрати за категорією '" + category + "': " + reportGenerator.getExpensesByCategory(category));
                    }
                    case "8" -> {
                        System.out.println("Вихід з програми");
                        return;
                    }
                    default -> System.out.println("Невідома команда. Спробуйте ще раз");
                }
            } catch (NumberFormatException e) {
                System.out.println("Помилка: Неправильний формат числа");
            } catch (InvalidTransactionData e) {
                System.out.println("Помилка валідації: " + e.getMessage());
            }
        }
    }

    private static void processTransactionInput(Scanner scanner, Tracker tracker, boolean isIncome) {
        System.out.print("Введіть суму: ");
        BigDecimal amount = new BigDecimal(scanner.nextLine().trim());

        System.out.print("Введіть дату (РРРР-ММ-ДД) або пропустіть для сьогоднішньої: ");
        String dateInput = scanner.nextLine().trim();
        LocalDate date;

        if (dateInput.isEmpty()) {
            date = LocalDate.now();
        } else {
            date = LocalDate.parse(dateInput);
        }

        System.out.print("Введіть категорію: ");
        String category = scanner.nextLine();

        System.out.print("Введіть опис: ");
        String description = scanner.nextLine();

        Transaction transaction = new Transaction(amount, date, isIncome, category, description);
        tracker.addTransaction(transaction);
        System.out.println("Транзакцію успішно додано.");
    }
}
