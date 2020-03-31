//package com.myntra.order.util;// Install the Java helper library from twilio.com/docs/libraries/java
//import com.twilio.Twilio;
//import com.twilio.rest.api.v2010.account.Message;
//import com.twilio.type.PhoneNumber;
//
//public class SmsSender {
//    // Find your Account Sid and Auth Token at twilio.com/console
//    public static final String ACCOUNT_SID =
//            "AC5b5f1c2111dc9f198b0163af37d25681";
//    public static final String AUTH_TOKEN =
//            "1da9e8368fc89add911708492c075fe3";
//
//    public static void main(String[] args) {
//        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
//
//        Message message = Message
//                .creator(new PhoneNumber("+918712947423"), // to
//                        new PhoneNumber("+12018014462"), // from
//                        "OTP-829076")
//                .create();
//
//        System.out.println(message.getSid());
//    }
//}
