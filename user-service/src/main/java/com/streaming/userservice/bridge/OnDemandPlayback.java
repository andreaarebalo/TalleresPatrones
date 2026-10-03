package com.streaming.userservice.bridge;

public class OnDemandPlayback extends StreamingPlayback {

    public OnDemandPlayback(StreamingRenderer renderer) {
        super(renderer);
    }

    @Override
    public String play(String title) {
        return render("A demanda: " + requireTitle(title));
    }

    private String requireTitle(String title) {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("El título no puede estar vacío");
        }
        return title;
    }
}
