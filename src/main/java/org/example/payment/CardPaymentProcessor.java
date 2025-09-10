package org.example.payment;

import org.example.exception.PaymentFailedException;
import org.example.model.Order;

public class CardPaymentProcessor extends PaymentGateway{
    @Override
    protected void authenticate() throws PaymentFailedException {
        System.out.println("🔑 Authenticating card details...");
    }

    @Override
    protected void process(Order order) throws PaymentFailedException {
        System.out.println("💳 Processing card payment of ₹" + order.getTotal());
    }
}
