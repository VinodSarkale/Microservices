package com.casestudy.subscriptionservice.repository;

import com.casestudy.subscriptionservice.entity.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, String> {

    Subscription findBySubscriberName(String subscriberName);
}
