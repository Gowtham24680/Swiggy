package org.example.services;

import org.example.exception.OrderFailedException;
import org.example.model.MenuItem;
import org.example.model.Order;
import org.example.model.Restaurant;
import org.example.model.User;

import java.util.List;

public abstract class Workflow {

    // Template Method
    public final void start(User user) throws OrderFailedException {

        authenticateUser(user);
        Restaurant restaurant = selectRestaurant();
        List<MenuItem> items = chooseItems(restaurant);
        Order order = placeOrder(user, restaurant, items);
        makePayment(order);
        complete(order);
    }

    protected abstract void authenticateUser(User user) throws OrderFailedException;
    protected abstract Restaurant selectRestaurant() throws OrderFailedException;
    protected abstract List<MenuItem> chooseItems(Restaurant restaurant) throws OrderFailedException;
    protected abstract Order placeOrder(User user, Restaurant restaurant, List<MenuItem> items) throws OrderFailedException;
    protected abstract void makePayment(Order order) throws OrderFailedException;

    protected void complete(Order order) {
        System.out.println("🎉 Order completed successfully: " + order);
    }
}
