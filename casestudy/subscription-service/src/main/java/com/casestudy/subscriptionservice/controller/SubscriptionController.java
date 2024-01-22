package com.casestudy.subscriptionservice.controller;

import com.casestudy.subscriptionservice.service.SubscriptionService;
import com.casestudy.subscriptionservice.entity.Subscription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@RequestMapping("/subscriptions")
public class SubscriptionController {

    @Autowired
    private SubscriptionService subscriptionService;

    @GetMapping("/subscriptions")
    public ResponseEntity<?> getAllSubscriptions() {
        List<Subscription> allSubscriptions = subscriptionService.getAllSubscriptions();
        return new ResponseEntity<>(allSubscriptions, HttpStatus.OK);
    }

    @GetMapping("/subscriptions/{subscriberName}")
    public ResponseEntity<?> getSubscriptionByName(@PathVariable String subscriberName) {
        Subscription subscription = subscriptionService.getSubscriptionByName(subscriberName);
        return new ResponseEntity<>(subscription, HttpStatus.OK);
    }


    @PostMapping("/subscriptions")
    public ResponseEntity<?> addSubscription(@RequestBody Subscription subscription) {
        String result = subscriptionService.addSubscription(subscription);
        String message;
        if(result.equalsIgnoreCase("SUCCESS")) {
            message = "Subscription added successfully!";
            return new ResponseEntity<>(message, HttpStatus.CREATED);
        } else if(result.equalsIgnoreCase("FAILURE")) {
            message = "Copies of specified book are not available for subscription.";
            return new ResponseEntity<>(message, HttpStatus.UNPROCESSABLE_ENTITY);
        } else {
            message = "Book not found for subscription.";
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
    }
}
