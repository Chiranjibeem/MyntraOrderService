package com.example.mailexchange.MailExchange;

import java.util.*;
import javax.mail.*; 
import javax.mail.internet.*; 
import javax.activation.*; 
  
public class SendMail { 
  
    public static void main(String[] args) { 
          
        final String username = "admin@s209476.cloud.flynet.pro";
        final String password = "ZrSzerhj";
        final String host = "s209476.cloud.flynet.pro";
        Properties props = new Properties();
        props.put("mail.smtp.auth", host);
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "25");
  
        Session session = Session.getInstance(props,
          new javax.mail.Authenticator() { 
             
            //override the getPasswordAuthentication method 
            protected PasswordAuthentication  
                           getPasswordAuthentication() { 
                return new PasswordAuthentication(username,
                                                 password); 
            } 
          }); 

        try {

            Message message = new MimeMessage(session);

            message.setFrom(new InternetAddress(username));

            message.setRecipients(Message.RecipientType.TO,
                InternetAddress.parse("chintu.mit@gmail.com"));
            message.setSubject("hello");
            message.setText("Yo it has been sent");

            Transport.send(message);         //send Message

            System.out.println("Done");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}