package com.streaming.userservice.factory.streaming;

public class Web4KStreamer implements VideoStreamer {
    @Override
    public String streamQuality() {
        return "Streaming Adaptativo Web: Resolucion 4K (Ultra HD)";
    }
}
