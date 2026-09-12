package com.streaming.userservice.prototype;

import java.util.HashMap;
import java.util.Map;

import com.streaming.userservice.builder.VideoBuilder;
import com.streaming.userservice.builder.VideoBuilder.Video;
import com.streaming.userservice.factory.streaming.DRMProtection;
import com.streaming.userservice.factory.streaming.VideoStreamer;

public class VideoPrototypeRegistry {

    private final Map<String, Video> plantillas = new HashMap<>();

    public void registrarPlantilla(String clave, Video plantilla) {
        plantillas.put(clave, plantilla);
    }

    public Video obtenerCopia(String clave) {
        Video plantilla = plantillas.get(clave);
        if (plantilla == null) {
            throw new IllegalArgumentException("No existe una plantilla registrada con la clave: " + clave);
        }
        try {
            return plantilla.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError("Video deberia ser siempre clonable", e);
        }
    }

    public static VideoPrototypeRegistry crearRegistroDeEjemplo(VideoStreamer streamer, DRMProtection drm) {
        VideoPrototypeRegistry registry = new VideoPrototypeRegistry();

        Video peliculaHD = new VideoBuilder("plantilla-001", "Plantilla Pelicula HD")
                .duracionMinutos(120)
                .categoria("Accion")
                .streamer(streamer)
                .drm(drm)
                .agregarSubtitulo("es")
                .build();

        Video serieMovil = new VideoBuilder("plantilla-002", "Plantilla Serie Movil")
                .duracionMinutos(45)
                .categoria("Drama")
                .streamer(streamer)
                .drm(drm)
                .agregarSubtitulo("es")
                .agregarSubtitulo("en")
                .build();

        registry.registrarPlantilla("peliculaHD", peliculaHD);
        registry.registrarPlantilla("serieMovil", serieMovil);

        return registry;
    }
}
