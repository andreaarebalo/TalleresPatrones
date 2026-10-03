package com.streaming.userservice.demo;

import com.streaming.userservice.factory.streaming.DRMProtection;
import com.streaming.userservice.factory.streaming.MobileStreamingFactory;
import com.streaming.userservice.factory.streaming.StreamingFactory;
import com.streaming.userservice.factory.streaming.VideoStreamer;
import com.streaming.userservice.factory.subscription.Subscription;
import com.streaming.userservice.factory.subscription.SubscriptionFactory;

public class FactoryMethodAbstractFactoryTest {

    public static void main(String[] args) {

        System.out.println("========================================");
        System.out.println(" PRUEBA 1: ABSTRACT FACTORY");
        System.out.println("========================================");
        System.out.println("(StreamingFactory crea una FAMILIA de objetos relacionados: streamer + DRM, juntos)");
        System.out.println();

        StreamingFactory abstractFactory = new MobileStreamingFactory();

        VideoStreamer streamer = abstractFactory.createStreamer();
        DRMProtection drm = abstractFactory.createDRM();

        System.out.println("Streamer creado: " + streamer.streamQuality());
        System.out.println("DRM creado: " + drm.applyDRM());

        System.out.println();
        System.out.println("========================================");
        System.out.println(" PRUEBA 2: FACTORY METHOD");
        System.out.println("========================================");
        System.out.println("(SubscriptionFactory decide QUE Subscription crear, segun el texto que le pases)");
        System.out.println();

        Subscription planFree = SubscriptionFactory.createSubscription("FREE");
        Subscription planPremium = SubscriptionFactory.createSubscription("PREMIUM");
        Subscription planPorDefecto = SubscriptionFactory.createSubscription(null); // prueba del caso por defecto

        System.out.println(planFree.getPlanName() + " - $" + planFree.getPrice()
                + " | Calidad maxima HD: " + planFree.getMaxQualityHD());

        System.out.println(planPremium.getPlanName() + " - $" + planPremium.getPrice()
                + " | Calidad maxima HD: " + planPremium.getMaxQualityHD());

        System.out.println("Plan con tipo null (usa el valor por defecto): " + planPorDefecto.getPlanName());
    }
}