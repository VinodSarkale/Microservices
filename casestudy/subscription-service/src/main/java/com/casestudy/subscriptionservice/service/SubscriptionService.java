package com.casestudy.subscriptionservice.service;

import com.casestudy.subscriptionservice.entity.Subscription;
import com.casestudy.subscriptionservice.repository.SubscriptionRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class SubscriptionService {

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    public List<Subscription> getAllSubscriptions() {
        return subscriptionRepository.findAll();
    }

    public Subscription getSubscriptionByName(String subscriberName) {
        return subscriptionRepository.findBySubscriberName(subscriberName);
    }

    @Transactional
    @CircuitBreaker(name = "subscriptionService", fallbackMethod = "fallBackMethodForSubscription")
    public String addSubscription(Subscription subscription) {
        RestTemplate restTemplate = new RestTemplate();
        String bookId = subscription.getBookId();
        ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://localhost:8081/books/" + bookId, String.class);
        String responseData = responseEntity.getBody();
        ObjectMapper mapper = new ObjectMapper();

        if(responseData != null) {
            try {
                JsonNode jsonData = mapper.readTree(responseData);
                String availableCopies = null;
                if (jsonData.get("copiesAvailable") != null) {
                    availableCopies = jsonData.get("copiesAvailable").asText();
                    if (Integer.valueOf(availableCopies) > 0) {
                        subscriptionRepository.save(subscription);
                        return "SUCCESS";
                    } else {
                        return "FAILURE";
                    }
                }
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        return "NOT FOUND";
    }

    public String fallBackMethodForSubscription(Subscription subscription, Throwable throwable) {
        System.out.println("Fallback method executed");
        return "Book service is down/taking longer than expected, please try after sometime.";
    }
}
