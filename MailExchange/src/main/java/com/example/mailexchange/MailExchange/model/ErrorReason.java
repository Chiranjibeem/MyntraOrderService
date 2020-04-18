package com.example.mailexchange.MailExchange.model;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity(name="ERROR_REASON")
@EntityListeners(AuditingEntityListener.class)
public class ErrorReason {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ERROR_ID")
    private int id;

    @Column(name = "FROM_EMAIL")
    private String fromEmail;

    @Column(name = "TO_EMAIL")
    private String toEmail;

    @Column(name = "ERROR_MSG")
    private String errorMsg;

    @CreatedDate
    @Column(name = "CREATED_DATE")
    private Timestamp createdDate;

    @LastModifiedDate
    @Column(name = "LAST_MODIFY_DATE")
    private Timestamp modifiedDate;



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getToEmail() {
        return toEmail;
    }

    public void setToEmail(String toEmail) {
        this.toEmail = toEmail;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    public Timestamp getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Timestamp modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public String getFromEmail() {
        return fromEmail;
    }

    public void setFromEmail(String fromEmail) {
        this.fromEmail = fromEmail;
    }
}


