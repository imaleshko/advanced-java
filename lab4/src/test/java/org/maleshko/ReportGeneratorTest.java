package org.maleshko;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ReportGeneratorTest {

    private ReportGenerator reportGenerator;
    private final LocalDate today = LocalDate.of(2026, 10, 15);
    private final LocalDate yesterday = LocalDate.of(2026, 10, 14);

    @BeforeEach
    void setUp() {
        Tracker tracker = new Tracker();
        reportGenerator = new ReportGenerator(tracker);

        tracker.addTransaction(new Transaction(new BigDecimal("1000"), today, true, "Зарплата", "Аванс"));
        tracker.addTransaction(new Transaction(new BigDecimal("500"), yesterday, true, "Зарплата", "Премія"));
        tracker.addTransaction(new Transaction(new BigDecimal("200"), today, true, "Фріланс", "Замовлення"));

        tracker.addTransaction(new Transaction(new BigDecimal("150"), today, false, "Їжа", "Обід"));
        tracker.addTransaction(new Transaction(new BigDecimal("50"), yesterday, false, "Їжа", "Кава"));
        tracker.addTransaction(new Transaction(new BigDecimal("300"), today, false, "Комуналка", "Світло"));
    }

    @Test
    void shouldCalculateTotalIncome() {
        assertEquals(new BigDecimal("1700"), reportGenerator.totalIncome());
    }

    @Test
    void shouldCalculateTotalExpense() {
        assertEquals(new BigDecimal("500"), reportGenerator.totalExpense());
    }

    @Test
    void shouldCalculateIncomeByCategory() {
        assertEquals(new BigDecimal("1500"), reportGenerator.getIncomeByCategory("Зарплата"));
        assertEquals(new BigDecimal("200"), reportGenerator.getIncomeByCategory("Фріланс"));
        assertEquals(BigDecimal.ZERO, reportGenerator.getIncomeByCategory("Неіснуюча"));
    }

    @Test
    void shouldCalculateExpensesByCategory() {
        assertEquals(new BigDecimal("200"), reportGenerator.getExpensesByCategory("Їжа"));
        assertEquals(new BigDecimal("300"), reportGenerator.getExpensesByCategory("Комуналка"));
        assertEquals(BigDecimal.ZERO, reportGenerator.getExpensesByCategory("Неіснуюча"));
    }

    @Test
    void shouldGetTransactionsByDate() {
        List<Transaction> todayTransactions = reportGenerator.getTransactionsByDate(today);
        List<Transaction> yesterdayTransactions = reportGenerator.getTransactionsByDate(yesterday);
        List<Transaction> emptyTransactions = reportGenerator.getTransactionsByDate(LocalDate.of(2020, 1, 1));

        assertEquals(4, todayTransactions.size());
        assertEquals(2, yesterdayTransactions.size());
        assertTrue(emptyTransactions.isEmpty());
    }
}
