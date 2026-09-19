package com.streaming.userservice.factory.streaming;

public class MobileStreamingFactory implements StreamingFactory {
    @Override public VideoStreamer createStreamer() { return new Mobile720pStreamer(); }
    @Override public DRMProtection createDRM() { return new FairPlayDRM(); }
}
