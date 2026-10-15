package org.maleshko;

public class InvalidTransactionData extends RuntimeException {
    public InvalidTransactionData(String message) {
        super(message);
    }
}
