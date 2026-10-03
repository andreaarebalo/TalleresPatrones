package com.streaming.userservice.decorator;

import java.util.Objects;

import com.streaming.userservice.factory.streaming.VideoStreamer;

/** Base de los decoradores; conserva la misma interfaz que el objeto envuelto. */
public abstract class VideoStreamerDecorator implements VideoStreamer {

    protected final VideoStreamer delegate;

    protected VideoStreamerDecorator(VideoStreamer delegate) {
        this.delegate = Objects.requireNonNull(delegate, "delegate no puede ser null");
    }
}
