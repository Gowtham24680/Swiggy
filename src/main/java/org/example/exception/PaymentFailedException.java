package org.example.exception;

public class PaymentFailedException extends Exception{
    public PaymentFailedException(String message) {
        super(message);
    }
}
