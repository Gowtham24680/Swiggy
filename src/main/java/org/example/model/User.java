package org.example.model;


public class User {
    private final String username;
    private final String password; // For demo only; never store plain text in real apps

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }

}
