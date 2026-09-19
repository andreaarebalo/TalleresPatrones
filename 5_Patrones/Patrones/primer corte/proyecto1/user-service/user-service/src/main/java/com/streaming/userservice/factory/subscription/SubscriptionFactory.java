package com.streaming.userservice.factory.subscription;

public class SubscriptionFactory {
    public static Subscription createSubscription(String type) {
        if (type == null) return new FreeSubscription();
        
        switch (type.toUpperCase()) {
            case "PREMIUM":
                return new PremiumSubscription();
            case "FREE":
            default:
                return new FreeSubscription();
        }
    }
}