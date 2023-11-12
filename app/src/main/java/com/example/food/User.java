package com.example.food;

public class User {
    private String username;
    private String email;
    private String password;
    private String uint_id;

    public User(String username, String email, String password, String uint_id) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.uint_id = uint_id;

    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getUntid() {
        return uint_id;
    }
}
