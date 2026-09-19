package com.streaming.userservice.factory.subscription;

public interface Subscription {
    String getPlanName();
    double getPrice();
    int getMaxQualityHD();
}
