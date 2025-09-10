package org.example.model;

import java.util.List;

public class Restaurant {
    private final int id;
    private final String name;
    private final List<MenuItem> menu;

    public Restaurant(int id, String name, List<MenuItem> menu) {
        this.id = id;
        this.name = name;
        this.menu = menu;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public List<MenuItem> getMenu() { return menu; }

    @Override
    public String toString() {
        return id + ". " + name;
    }
}
