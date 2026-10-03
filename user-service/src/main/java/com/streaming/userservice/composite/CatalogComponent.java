package com.streaming.userservice.composite;

/** Componente común para hojas (videos) y colecciones del catálogo. */
public interface CatalogComponent {
    String name();
    int durationMinutes();
    int videoCount();
    String describe();
}
