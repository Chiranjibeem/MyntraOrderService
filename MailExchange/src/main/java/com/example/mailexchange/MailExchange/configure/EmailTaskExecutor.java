package com.example.mailexchange.MailExchange.configure;

import com.example.mailexchange.MailExchange.model.EmailStatus;

import java.util.concurrent.Callable;

public class EmailTaskExecutor implements Callable<EmailStatus> {

    private EmailServiceImpl emailService;

    private String fomEmail;
    private String personName;
    private String subjectName;
    private String message;
    private String toEmail;
    private String toEmailName;

    public EmailTaskExecutor(String fomEmail, String personName, String subjectName, String message, String toEmail,String toEmailName,EmailServiceImpl emailService) {
        this.fomEmail = fomEmail;
        this.personName = personName;
        this.subjectName = subjectName;
        this.message = message;
        this.toEmail = toEmail;
        this.toEmailName = toEmailName;
        this.emailService = emailService;
    }

    @Override
    public EmailStatus call() throws Exception {
        return emailService.sendEmailWithAttachment(this.fomEmail,this.personName,this.subjectName,this.message,this.toEmail,this.toEmailName);
    }
}
