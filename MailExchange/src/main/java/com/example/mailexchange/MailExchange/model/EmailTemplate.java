package com.example.mailexchange.MailExchange.model;

import java.util.HashMap;

public class EmailTemplate {
    private String email;
    private String name;

    public static HashMap<String,String> hashMap= new HashMap<>();

    static {
        hashMap.put("email", "email");
        hashMap.put("name", "name");
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
