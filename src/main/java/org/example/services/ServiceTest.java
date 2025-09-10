package org.example.services;

import org.example.exception.OrderFailedException;
import org.example.model.MenuItem;
import org.example.model.Restaurant;
import org.example.model.User;
import java.util.Arrays;
import java.util.List;

public class ServiceTest {
    public static void main(String[] args) {
        User user = new User("Gowtham", "123456");

        List<Restaurant> restaurants = Arrays.asList(
                new Restaurant(1, "Andhra Spice", Arrays.asList(
                        new MenuItem(1, "Chicken Biryani", 200),
                        new MenuItem(2, "Paneer Butter Masala", 180))),
                new Restaurant(2, "Udupi Palace", Arrays.asList(
                        new MenuItem(1, "Masala Dosa", 100),
                        new MenuItem(2, "Idli Vada", 70)))
        );

        OrderWorkflow workflow = new OrderWorkflow(restaurants);
        try {
            workflow.start(user);
        } catch (OrderFailedException e) {
            System.out.println("🚨 Order failed: " + e.getMessage());
        }
    }
}
