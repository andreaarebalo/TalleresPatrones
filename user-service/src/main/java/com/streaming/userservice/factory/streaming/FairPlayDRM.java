package com.streaming.userservice.factory.streaming;

public class FairPlayDRM implements DRMProtection {
    @Override
    public String applyDRM() {
        return "Proteccion DRM: Apple FairPlay activado para Dispositivo Movil";
    }
}
