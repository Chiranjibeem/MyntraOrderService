package com.myntra.order.model;

import javax.persistence.*;

@Entity(name="ORD_BOOK_TABLE")
public class BookTable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="BOOKING_ID")
    private int bookingId;

    @Column(name="BOOKING_DATE")
    private String bookDate;

    @Column(name="BOOKING_TIME")
    private String bookTime;

    @Column(name="NO_OF_PERSON")
    private String noOfPerson;

    @Column(name="PERSON_NAME")
    private String personName;

    @Column(name="PERSON_EMAIL")
    private String personEmailId;

    @Column(name="PERSON_PH_NO")
    private String personPhoneNumber;

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public String getBookDate() {
        return bookDate;
    }

    public void setBookDate(String bookDate) {
        this.bookDate = bookDate;
    }

    public String getBookTime() {
        return bookTime;
    }

    public void setBookTime(String bookTime) {
        this.bookTime = bookTime;
    }

    public String getNoOfPerson() {
        return noOfPerson;
    }

    public void setNoOfPerson(String noOfPerson) {
        this.noOfPerson = noOfPerson;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getpersonEmailId() {
        return personEmailId;
    }

    public void setpersonEmailId(String personEmailId) {
        this.personEmailId = personEmailId;
    }

    public String getPersonPhoneNumber() {
        return personPhoneNumber;
    }

    public void setPersonPhoneNumber(String personPhoneNumber) {
        this.personPhoneNumber = personPhoneNumber;
    }
}
