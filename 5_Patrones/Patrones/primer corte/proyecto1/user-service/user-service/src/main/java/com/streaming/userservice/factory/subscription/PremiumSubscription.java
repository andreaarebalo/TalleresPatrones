package com.streaming.userservice.factory.subscription;

public class PremiumSubscription implements Subscription {
    @Override public String getPlanName() { return "PREMIUM 4K"; }
    @Override public double getPrice() { return 14.99; }
    @Override public int getMaxQualityHD() { return 2160; }
}
