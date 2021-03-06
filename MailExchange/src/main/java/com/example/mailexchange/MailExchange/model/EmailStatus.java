package com.example.mailexchange.MailExchange.model;

import java.util.HashMap;

public class EmailStatus {
    public static HashMap<String, String> hashMap = new HashMap<>();

    static {
        hashMap.put("email", "email");
        hashMap.put("name", "name");
        hashMap.put("status", "status");
    }

    private String email;
    private String name;
    private String status;

    public EmailStatus(String email, String name, String status) {
        this.email = email;
        this.name = name;
        this.status = status;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
