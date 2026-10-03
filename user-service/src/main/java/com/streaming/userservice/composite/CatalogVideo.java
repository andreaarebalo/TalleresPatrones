package com.streaming.userservice.composite;

public class CatalogVideo implements CatalogComponent {

    private final String name;
    private final int durationMinutes;

    public CatalogVideo(String name, int durationMinutes) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("El título no puede estar vacío");
        }
        if (durationMinutes <= 0) {
            throw new IllegalArgumentException("La duración debe ser mayor que cero");
        }
        this.name = name;
        this.durationMinutes = durationMinutes;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public int durationMinutes() {
        return durationMinutes;
    }

    @Override
    public int videoCount() {
        return 1;
    }

    @Override
    public String describe() {
        return "Video: " + name + " (" + durationMinutes + " min)";
    }
}
