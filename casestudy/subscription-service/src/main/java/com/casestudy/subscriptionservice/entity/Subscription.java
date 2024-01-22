package com.casestudy.subscriptionservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.sql.Date;

@Entity
public class Subscription {

    @Id
    private String subscriptionId;
    private String subscriberName;
    private String bookId;
    private Date dateSubscribed;
    private Date dateReturned;


    public Subscription() {
        super();
    }

    public Subscription(String subscriptionId, String subscriberName, String bookId, Date dateSubscribed, Date dateReturned) {
        this.subscriptionId = subscriptionId;
        this.subscriberName = subscriberName;
        this.bookId = bookId;
        this.dateSubscribed = dateSubscribed;
        this.dateReturned = dateReturned;
    }

    public String getSubscriptionId() {
        return subscriptionId;
    }

    public void setSubscriptionId(String subscriptionId) {
        this.subscriptionId = subscriptionId;
    }

    public String getSubscriberName() {
        return subscriberName;
    }

    public void setSubscriberName(String subscriberName) {
        this.subscriberName = subscriberName;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public Date getDateSubscribed() {
        return dateSubscribed;
    }

    public void setDateSubscribed(Date dateSubscribed) {
        this.dateSubscribed = dateSubscribed;
    }

    public Date getDateReturned() {
        return dateReturned;
    }

    public void setDateReturned(Date dateReturned) {
        this.dateReturned = dateReturned;
    }
}
