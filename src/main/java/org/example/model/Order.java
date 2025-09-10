package org.example.model;

import java.util.List;

public class Order {
    private final int id;
    private final User user;
    private final Restaurant restaurant;
    private final List<MenuItem> items;
    private final double total;

    public Order(int id, User user, Restaurant restaurant, List<MenuItem> items, double total) {
        this.id = id;
        this.user = user;
        this.restaurant = restaurant;
        this.items = items;
        this.total = total;
    }

    public int getId() { return id; }
    public User getUser() { return user; }
    public Restaurant getRestaurant() { return restaurant; }
    public List<MenuItem> getItems() { return items; }
    public double getTotal() { return total; }

    @Override
    public String toString() {
        return "Order#" + id + " | User: " + user.getUsername() +
                " | Restaurant: " + restaurant.getName() + " | Total: ₹" + total;
    }
}
