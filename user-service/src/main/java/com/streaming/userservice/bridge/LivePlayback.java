package com.streaming.userservice.bridge;

public class LivePlayback extends StreamingPlayback {

    public LivePlayback(StreamingRenderer renderer) {
        super(renderer);
    }

    @Override
    public String play(String title) {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("El título no puede estar vacío");
        }
        return render("En vivo: " + title);
    }
}
