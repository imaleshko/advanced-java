package org.maleshko;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ReportGenerator {
    private final Tracker tracker;

    public ReportGenerator(Tracker tracker) {
        this.tracker = tracker;
    }

    public BigDecimal totalIncome() {
        BigDecimal total = BigDecimal.ZERO;
        for (List<Transaction> transactionsCategory : tracker.getIncomeTransactions().values()) {
            for (Transaction transaction : transactionsCategory) {
                total = total.add(transaction.amount());
            }
        }
        return total;
    }

    public BigDecimal totalExpense() {
        BigDecimal total = BigDecimal.ZERO;
        for (List<Transaction> transactionsCategory : tracker.getExpenseTransactions().values()) {
            for (Transaction transaction : transactionsCategory) {
                total = total.add(transaction.amount());
            }
        }
        return total;
    }

    public BigDecimal getIncomeByCategory(String category) {
        List<Transaction> transactions = tracker.getIncomeTransactions().getOrDefault(category, Collections.emptyList());
        BigDecimal total = BigDecimal.ZERO;
        for (Transaction transaction : transactions) {
            total = total.add(transaction.amount());
        }
        return total;
    }

    public BigDecimal getExpensesByCategory(String category) {
        List<Transaction> transactions = tracker.getExpenseTransactions().getOrDefault(category, Collections.emptyList());
        BigDecimal total = BigDecimal.ZERO;
        for (Transaction transaction : transactions) {
            total = total.add(transaction.amount());
        }
        return total;
    }

    public List<Transaction> getTransactionsByDate(LocalDate targetDate) {
        List<Transaction> result = new ArrayList<>();

        for (List<Transaction> transactions : tracker.getIncomeTransactions().values()) {
            for (Transaction transaction : transactions) {
                if (transaction.date().equals(targetDate)) {
                    result.add(transaction);
                }
            }
        }

        for (List<Transaction> transactions : tracker.getExpenseTransactions().values()) {
            for (Transaction transaction : transactions) {
                if (transaction.date().equals(targetDate)) {
                    result.add(transaction);
                }
            }
        }

        return result;
    }
}
