package com.streaming.userservice.bridge;

public class MobileRenderer implements StreamingRenderer {
    @Override
    public String render(String contentDescription) {
        return contentDescription + " renderizado en móvil (720p)";
    }
}
