package com.streaming.userservice.factory.subscription;

public class FreeSubscription implements Subscription {
    @Override public String getPlanName() { return "GRATUITO"; }
    @Override public double getPrice() { return 0.0; }
    @Override public int getMaxQualityHD() { return 720; }
}
