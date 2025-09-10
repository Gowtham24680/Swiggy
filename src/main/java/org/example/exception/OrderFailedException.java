package org.example.exception;

public class OrderFailedException extends Exception{
    public OrderFailedException(String message) {
        super(message);
    }
}
