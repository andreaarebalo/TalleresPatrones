package com.streaming.userservice.bridge;

/** Implementación que renderiza el contenido en una plataforma concreta. */
public interface StreamingRenderer {
    String render(String contentDescription);
}
