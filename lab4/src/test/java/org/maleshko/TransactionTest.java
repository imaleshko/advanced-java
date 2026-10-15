package org.maleshko;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class TransactionTest {
    @Test
    void shouldCreateValidTransaction() {
        LocalDate date = LocalDate.of(2026, 10, 15);
        Transaction transaction = new Transaction(new BigDecimal("150.50"), date, true, "Зарплата", "Аванс");

        assertEquals(new BigDecimal("150.50"), transaction.amount());
        assertEquals(date, transaction.date());
        assertTrue(transaction.isIncome());
        assertEquals("Зарплата", transaction.category());
        assertEquals("Аванс", transaction.description());
    }

    @Test
    void shouldSetCurrentDateWhenDateIsNull() {
        Transaction transaction = new Transaction(new BigDecimal("100"), null, false, "Їжа", "Обід");

        assertEquals(LocalDate.now(), transaction.date());
    }

    @Test
    void shouldThrowExceptionWhenAmountIsInvalid() {
        assertThrows(InvalidTransactionData.class, () ->
                new Transaction(null, LocalDate.now(), true, "Категорія", "Опис")
        );
        assertThrows(InvalidTransactionData.class, () ->
                new Transaction(BigDecimal.ZERO, LocalDate.now(), true, "Категорія", "Опис")
        );
        assertThrows(InvalidTransactionData.class, () ->
                new Transaction(new BigDecimal("-50"), LocalDate.now(), true, "Категорія", "Опис")
        );
    }

    @Test
    void shouldThrowExceptionWhenCategoryIsInvalid() {
        assertThrows(InvalidTransactionData.class, () ->
                new Transaction(new BigDecimal("100"), LocalDate.now(), true, null, "Опис")
        );
        assertThrows(InvalidTransactionData.class, () ->
                new Transaction(new BigDecimal("100"), LocalDate.now(), true, "   ", "Опис")
        );
    }
}
