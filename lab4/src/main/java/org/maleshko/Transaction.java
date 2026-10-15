package org.maleshko;

import java.math.BigDecimal;
import java.time.LocalDate;

public record Transaction(BigDecimal amount, LocalDate date, boolean isIncome, String category, String description) {
    public Transaction(BigDecimal amount, LocalDate date, boolean isIncome, String category, String description) {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidTransactionData("Сума повинна бути більшою за нуль");
        }
        if (category == null || category.trim().isEmpty()) {
            throw new InvalidTransactionData("Категорія повинна бути вказана");
        }

        this.amount = amount;
        this.date = date != null ? date : LocalDate.now();
        this.isIncome = isIncome;
        this.category = category;
        this.description = description;
    }
}
