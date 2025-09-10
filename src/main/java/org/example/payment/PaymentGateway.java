package org.example.payment;

import org.example.exception.PaymentFailedException;
import org.example.model.Order;

public abstract class PaymentGateway implements PaymentStrategy {

    // Template Method
    @Override
    public final void pay(Order order) throws PaymentFailedException {
        validate(order);
        authenticate();
        process(order);
        sendConfirmation(order);
    }

    protected void validate(Order order) throws PaymentFailedException {
        if (order == null || order.getTotal() <= 0) {
            throw new PaymentFailedException("Invalid order for payment.");
        }
        System.out.println("✅ Validation successful for Order#" + order.getId());
    }

    protected abstract void authenticate() throws PaymentFailedException;
    protected abstract void process(Order order) throws PaymentFailedException;

    protected void sendConfirmation(Order order) {
        System.out.println("📩 Payment confirmation sent for Order#" + order.getId());
    }
}
