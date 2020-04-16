package com.example.mailexchange.MailExchange.model;

import com.sun.org.apache.xpath.internal.operations.Mult;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

public class SendNewEmail {

    private String senderEmail;
    private MultipartFile template;
    private String senderName;
    private String subject;
    private String message;

    public String getSenderEmail() {
        return senderEmail;
    }

    public void setSenderEmail(String senderEmail) {
        this.senderEmail = senderEmail;
    }

    public MultipartFile getTemplate() {
        return template;
    }

    public void setTemplate(MultipartFile template) {
        this.template = template;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
