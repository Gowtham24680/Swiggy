package org.example.services;

import org.example.exception.OrderFailedException;
import org.example.exception.PaymentFailedException;
import org.example.model.MenuItem;
import org.example.model.Order;
import org.example.model.Restaurant;
import org.example.model.User;
import org.example.payment.CardPaymentProcessor;
import org.example.payment.PaymentStrategy;
import org.example.payment.UPIPaymentProcessor;
import org.example.payment.WalletPaymentProcessor;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class OrderWorkflow extends Workflow {

    private Scanner sc = new Scanner(System.in);
    private List<Restaurant> restaurants;

    public OrderWorkflow(List<Restaurant> restaurants) {
        this.restaurants = restaurants;
    }

    @Override
    protected void authenticateUser(User user) throws OrderFailedException {
        if (user == null) throw new OrderFailedException("❌ User not authenticated.");
        System.out.println("👤 User authenticated: " + user.getUsername());
    }

    @Override
    protected Restaurant selectRestaurant() throws OrderFailedException {
        System.out.println("\n🍽 Available Restaurants:");
        restaurants.forEach(System.out::println);

        System.out.print("Enter restaurant ID: ");
        int restId = sc.nextInt();
        sc.nextLine(); // consume newline
        return restaurants.stream()
                .filter(r -> r.getId() == restId)
                .findFirst()
                .orElseThrow(() -> new OrderFailedException("❌ Invalid restaurant selection"));
    }

    @Override
    protected List<MenuItem> chooseItems(Restaurant restaurant) throws OrderFailedException {
        System.out.println("\n📋 Menu of " + restaurant.getName() + ":");
        restaurant.getMenu().forEach(System.out::println);

        System.out.print("Enter item IDs separated by commas: ");
        String[] ids = sc.nextLine().split(",");
        List<MenuItem> selected = new ArrayList<>();

        for (String idStr : ids) {
            int id = Integer.parseInt(idStr.trim());
            restaurant.getMenu().stream()
                    .filter(m -> m.getId() == id)
                    .findFirst()
                    .ifPresent(selected::add);
        }
        if (selected.isEmpty()) throw new OrderFailedException("❌ No items selected.");
        return selected;
    }

    @Override
    protected Order placeOrder(User user, Restaurant restaurant, List<MenuItem> items) throws OrderFailedException {
        double total = items.stream().mapToDouble(MenuItem::getPrice).sum();
        Order order = new Order(new Random().nextInt(10000), user, restaurant, items, total);
        System.out.println("🛒 Order placed: " + order);
        return order;
    }

    @Override
    protected void makePayment(Order order) throws OrderFailedException {
        try {
            System.out.print("💳 Choose payment method (UPI/Card/Wallet): ");
            String method = sc.nextLine();

            PaymentStrategy strategy;
            switch (method.toLowerCase()) {
                case "upi": strategy = new UPIPaymentProcessor(); break;
                case "card": strategy = new CardPaymentProcessor(); break;
                case "wallet": strategy = new WalletPaymentProcessor(); break;
                default: throw new OrderFailedException("❌ Unsupported payment method");
            }

            strategy.pay(order); // Strategy + Template together

        } catch (PaymentFailedException e) {
            throw new OrderFailedException("❌ Payment failed: " + e.getMessage());
        }
    }
}
