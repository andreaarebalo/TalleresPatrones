package com.streaming.userservice.demo;

import com.streaming.userservice.builder.VideoBuilder;
import com.streaming.userservice.builder.VideoBuilder.Video;
import com.streaming.userservice.factory.streaming.MobileStreamingFactory;
import com.streaming.userservice.factory.streaming.StreamingFactory;
import com.streaming.userservice.prototype.VideoPrototypeRegistry;

public class BuilderPrototypeTest {

    public static void main(String[] args) {

        StreamingFactory factory = new MobileStreamingFactory();

        System.out.println("========================================");
        System.out.println(" PRUEBA 1: PATRON BUILDER");
        System.out.println("========================================");

        Video video1 = new VideoBuilder("v001", "Duelo Final")
                .descripcion("Una pelicula de accion intensa")
                .duracionMinutos(128)
                .categoria("Accion")
                .streamer(factory.createStreamer())
                .drm(factory.createDRM())
                .agregarSubtitulo("es")
                .agregarSubtitulo("en")
                .build();

        System.out.println("Video construido con VideoBuilder:");
        System.out.println(video1);
        System.out.println(video1.reproducir());

        System.out.println();
        System.out.println("========================================");
        System.out.println(" PRUEBA 2: PATRON PROTOTYPE");
        System.out.println("========================================");

        VideoPrototypeRegistry registry = VideoPrototypeRegistry
                .crearRegistroDeEjemplo(factory.createStreamer(), factory.createDRM());

        System.out.println("Plantillas registradas: peliculaHD, serieMovil");

        Video copiaA = registry.obtenerCopia("peliculaHD");
        Video copiaB = registry.obtenerCopia("peliculaHD");

        System.out.println("\nCopia A (clonada de la plantilla 'peliculaHD'):");
        System.out.println(copiaA);

        System.out.println("\nCopia B (otra clonacion de la misma plantilla):");
        System.out.println(copiaB);

        System.out.println("\n¿Copia A y Copia B son el mismo objeto en memoria? "
                + (copiaA == copiaB) + "  (debe ser false: son clones distintos)");
        System.out.println("¿Tienen los mismos datos? "
                + copiaA.toString().equals(copiaB.toString()) + "  (debe ser true)");
    }
}