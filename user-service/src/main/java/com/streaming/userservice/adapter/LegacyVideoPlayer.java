package com.streaming.userservice.adapter;

/** API heredada cuya operación no coincide con la interfaz VideoStreamer. */
public class LegacyVideoPlayer {

    public String reproducirConResolucion(String resolucion) {
        return "Reproductor heredado: reproduciendo en " + resolucion;
    }
}
