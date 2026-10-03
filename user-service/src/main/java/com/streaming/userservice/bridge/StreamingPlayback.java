package com.streaming.userservice.bridge;

import java.util.Objects;

/** Abstracción independiente de la plataforma concreta de renderizado. */
public abstract class StreamingPlayback {

    private final StreamingRenderer renderer;

    protected StreamingPlayback(StreamingRenderer renderer) {
        this.renderer = Objects.requireNonNull(renderer, "renderer no puede ser null");
    }

    protected String render(String description) {
        return renderer.render(description);
    }

    public abstract String play(String title);
}
