package com.streaming.userservice.factory.streaming;

public interface StreamingFactory {
    VideoStreamer createStreamer();
    DRMProtection createDRM();
}
