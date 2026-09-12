package com.streaming.userservice.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.streaming.userservice.factory.streaming.DRMProtection;
import com.streaming.userservice.factory.streaming.MobileStreamingFactory;
import com.streaming.userservice.factory.streaming.StreamingFactory;
import com.streaming.userservice.factory.streaming.VideoStreamer;
import com.streaming.userservice.factory.streaming.WebStreamingFactory;
import com.streaming.userservice.factory.subscription.Subscription;
import com.streaming.userservice.factory.subscription.SubscriptionFactory;
import com.streaming.userservice.model.User;
import com.streaming.userservice.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    // 1. Gestión de usuarios en PostgreSQL
    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        return ResponseEntity.ok(userService.registerUser(user));
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    // 2. Sistema de recomendación de contenido (Simulación simple)
    @GetMapping("/recommendations")
    public ResponseEntity<List<String>> getRecommendations() {
        List<String> recomendados = Arrays.asList(
            "Pelicula: Inception",
            "Serie: Stranger Things",
            "Documental: Our Planet"
        );
        return ResponseEntity.ok(recomendados);
    }

    // 3. FACTORY METHOD: Gestión de Suscripciones
    @GetMapping("/subscription-info")
    public ResponseEntity<Map<String, Object>> getSubscriptionInfo(@RequestParam(defaultValue = "FREE") String type) {
        Subscription subscription = SubscriptionFactory.createSubscription(type);

        Map<String, Object> response = new HashMap<>();
        response.put("plan", subscription.getPlanName());
        response.put("precio", subscription.getPrice());
        response.put("calidadMax", subscription.getMaxQualityHD() + "p");

        return ResponseEntity.ok(response);
    }

    // 4. ABSTRACT FACTORY: Streaming Adaptativo + Protección DRM según dispositivo
    @GetMapping("/play")
    public ResponseEntity<Map<String, String>> playContent(@RequestParam(defaultValue = "web") String device) {
        StreamingFactory factory;

        if ("mobile".equalsIgnoreCase(device)) {
            factory = new MobileStreamingFactory();
        } else {
            factory = new WebStreamingFactory();
        }

        VideoStreamer streamer = factory.createStreamer();
        DRMProtection drm = factory.createDRM();

        Map<String, String> response = new HashMap<>();
        response.put("dispositivo", device.toUpperCase());
        response.put("streamingAdaptativo", streamer.streamQuality());
        response.put("gestionDRM", drm.applyDRM());

        return ResponseEntity.ok(response);
    }
}
