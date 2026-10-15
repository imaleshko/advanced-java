package org.maleshko;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Tracker {
    private BigDecimal balance;
    private final Map<String, List<Transaction>> incomeTransactions;
    private final Map<String, List<Transaction>> expenseTransactions;

    public Tracker() {
        balance = BigDecimal.ZERO;
        incomeTransactions = new HashMap<>();
        expenseTransactions = new HashMap<>();
    }

    public void addTransaction(Transaction transaction) {
        if (transaction.isIncome()) {
            incomeTransactions.computeIfAbsent(transaction.category(), _ -> new ArrayList<>()).add(transaction);
            balance = balance.add(transaction.amount());
        } else {
            expenseTransactions.computeIfAbsent(transaction.category(), _ -> new ArrayList<>()).add(transaction);
            balance = balance.subtract(transaction.amount());
        }
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public Map<String, List<Transaction>> getIncomeTransactions() {
        return Map.copyOf(incomeTransactions);
    }

    public Map<String, List<Transaction>> getExpenseTransactions() {
        return Map.copyOf(expenseTransactions);
    }
}
