package org.example.payment;

import org.example.exception.PaymentFailedException;
import org.example.model.Order;

public class UPIPaymentProcessor extends PaymentGateway{
    @Override
    protected void authenticate() throws PaymentFailedException {
        System.out.println("🔑 Authenticating UPI credentials...");
    }

    @Override
    protected void process(Order order) throws PaymentFailedException {
        System.out.println("💸 Processing UPI payment of ₹" + order.getTotal());
    }
}
