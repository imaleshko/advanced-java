package org.maleshko;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TrackerTest {
    private Tracker tracker;

    @BeforeEach
    void setUp() {
        tracker = new Tracker();
    }

    @Test
    void shouldHaveZeroBalanceInitially() {
        assertEquals(BigDecimal.ZERO, tracker.getBalance());
        assertTrue(tracker.getIncomeTransactions().isEmpty());
        assertTrue(tracker.getExpenseTransactions().isEmpty());
    }

    @Test
    void shouldAddIncomeAndIncreaseBalance() {
        Transaction income = new Transaction(new BigDecimal("1000"), LocalDate.now(), true, "Зарплата", "Основна");

        tracker.addTransaction(income);

        assertEquals(new BigDecimal("1000"), tracker.getBalance());
        assertEquals(1, tracker.getIncomeTransactions().get("Зарплата").size());
    }

    @Test
    void shouldAddExpenseAndDecreaseBalance() {
        Transaction expense = new Transaction(new BigDecimal("300"), LocalDate.now(), false, "Продукти", "Супермаркет");

        tracker.addTransaction(expense);

        assertEquals(new BigDecimal("-300"), tracker.getBalance());
        assertEquals(1, tracker.getExpenseTransactions().get("Продукти").size());
    }

    @Test
    void shouldCalculateBalanceWithMultipleTransactions() {
        tracker.addTransaction(new Transaction(new BigDecimal("1000"), LocalDate.now(), true, "Зарплата", ""));
        tracker.addTransaction(new Transaction(new BigDecimal("250"), LocalDate.now(), false, "Їжа", ""));
        tracker.addTransaction(new Transaction(new BigDecimal("150"), LocalDate.now(), false, "Транспорт", ""));

        assertEquals(new BigDecimal("600"), tracker.getBalance());
    }
}
