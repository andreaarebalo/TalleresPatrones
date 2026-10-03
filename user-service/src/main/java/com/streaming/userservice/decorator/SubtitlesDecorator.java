package com.streaming.userservice.decorator;

import com.streaming.userservice.factory.streaming.VideoStreamer;

public class SubtitlesDecorator extends VideoStreamerDecorator {

    private final String language;

    public SubtitlesDecorator(VideoStreamer delegate, String language) {
        super(delegate);
        if (language == null || language.isBlank()) {
            throw new IllegalArgumentException("El idioma de los subtítulos no puede estar vacío");
        }
        this.language = language;
    }

    @Override
    public String streamQuality() {
        return delegate.streamQuality() + " | subtítulos: " + language;
    }
}
