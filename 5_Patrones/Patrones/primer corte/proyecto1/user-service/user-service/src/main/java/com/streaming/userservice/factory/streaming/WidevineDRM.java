package com.streaming.userservice.factory.streaming;

public class WidevineDRM implements DRMProtection {
    @Override
    public String applyDRM() {
        return "Proteccion DRM: Widevine L1 activado para Navegador Web";
    }
}
