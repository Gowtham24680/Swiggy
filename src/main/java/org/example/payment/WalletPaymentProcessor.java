package org.example.payment;

import org.example.exception.PaymentFailedException;
import org.example.model.Order;

public class WalletPaymentProcessor extends PaymentGateway{
    @Override
    protected void authenticate() throws PaymentFailedException {
        System.out.println("🔑 Authenticating wallet balance...");
    }

    @Override
    protected void process(Order order) throws PaymentFailedException {
        System.out.println("👛 Deducting ₹" + order.getTotal() + " from wallet.");
    }
}
