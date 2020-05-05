package com.example.mailexchange.MailExchange.model;

import java.util.HashMap;

public class EmailStatus {
    private String email;
    private String status;

    public static HashMap<String,String> hashMap= new HashMap<>();

    static {
        hashMap.put("email", "email");
        hashMap.put("status", "status");
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

}
