package org.example.payment;


import org.example.exception.PaymentFailedException;
import org.example.model.Order;

public interface PaymentStrategy {
    void pay(Order order) throws PaymentFailedException;
}