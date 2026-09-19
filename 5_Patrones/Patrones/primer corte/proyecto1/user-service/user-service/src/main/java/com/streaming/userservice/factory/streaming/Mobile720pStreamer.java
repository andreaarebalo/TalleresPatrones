package com.streaming.userservice.factory.streaming;

public class Mobile720pStreamer implements VideoStreamer {
    @Override
    public String streamQuality() {
        return "Streaming Adaptativo Movil: Resolucion 720p HD";
    }
}
