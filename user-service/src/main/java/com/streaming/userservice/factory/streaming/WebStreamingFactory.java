package com.streaming.userservice.factory.streaming;

public class WebStreamingFactory implements StreamingFactory {
    @Override public VideoStreamer createStreamer() { return new Web4KStreamer(); }
    @Override public DRMProtection createDRM() { return new WidevineDRM(); }
}
