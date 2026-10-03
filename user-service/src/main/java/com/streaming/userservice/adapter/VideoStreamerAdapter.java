package com.streaming.userservice.adapter;

import java.util.Objects;

import com.streaming.userservice.factory.streaming.VideoStreamer;

/** Adapta la API del reproductor heredado a la interfaz VideoStreamer del proyecto. */
public class VideoStreamerAdapter implements VideoStreamer {

    private final LegacyVideoPlayer legacyPlayer;
    private final String resolution;

    public VideoStreamerAdapter(LegacyVideoPlayer legacyPlayer, String resolution) {
        this.legacyPlayer = Objects.requireNonNull(legacyPlayer, "legacyPlayer no puede ser null");
        if (resolution == null || resolution.isBlank()) {
            throw new IllegalArgumentException("La resolución no puede estar vacía");
        }
        this.resolution = resolution;
    }

    @Override
    public String streamQuality() {
        return "Adapter -> " + legacyPlayer.reproducirConResolucion(resolution);
    }
}
