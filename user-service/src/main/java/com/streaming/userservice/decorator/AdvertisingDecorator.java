package com.streaming.userservice.decorator;

import com.streaming.userservice.factory.streaming.VideoStreamer;

public class AdvertisingDecorator extends VideoStreamerDecorator {

    public AdvertisingDecorator(VideoStreamer delegate) {
        super(delegate);
    }

    @Override
    public String streamQuality() {
        return delegate.streamQuality() + " | anuncios: incluidos en el plan gratuito";
    }
}
