package com.streaming.userservice.bridge;

public class WebRenderer implements StreamingRenderer {
    @Override
    public String render(String contentDescription) {
        return contentDescription + " renderizado en Web (4K)";
    }
}
