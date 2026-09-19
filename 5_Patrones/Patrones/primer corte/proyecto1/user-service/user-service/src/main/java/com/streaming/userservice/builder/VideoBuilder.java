package com.streaming.userservice.builder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.streaming.userservice.factory.streaming.DRMProtection;
import com.streaming.userservice.factory.streaming.VideoStreamer;

public class VideoBuilder {

    private final String id;
    private final String titulo;

    private String descripcion = "";
    private int duracionMinutos = 0;
    private String categoria = "General";
    private VideoStreamer streamer = null;
    private DRMProtection drm = null;
    private final List<String> subtitulos = new ArrayList<>();

    public VideoBuilder(String id, String titulo) {
        this.id = id;
        this.titulo = titulo;
    }

    public VideoBuilder descripcion(String descripcion) {
        this.descripcion = descripcion;
        return this;
    }

    public VideoBuilder duracionMinutos(int duracionMinutos) {
        this.duracionMinutos = duracionMinutos;
        return this;
    }

    public VideoBuilder categoria(String categoria) {
        this.categoria = categoria;
        return this;
    }

    public VideoBuilder streamer(VideoStreamer streamer) {
        this.streamer = streamer;
        return this;
    }

    public VideoBuilder drm(DRMProtection drm) {
        this.drm = drm;
        return this;
    }

    public VideoBuilder agregarSubtitulo(String idioma) {
        this.subtitulos.add(idioma);
        return this;
    }

    public Video build() {
        if (id == null || id.isEmpty()) {
            throw new IllegalStateException("El video debe tener un id.");
        }
        if (titulo == null || titulo.isEmpty()) {
            throw new IllegalStateException("El video debe tener un titulo.");
        }
        if (streamer == null) {
            throw new IllegalStateException("El video debe tener un VideoStreamer (usa factory.createStreamer()).");
        }
        if (drm == null) {
            throw new IllegalStateException("El video debe tener un DRMProtection (usa factory.createDRM()).");
        }

        return new Video(
                id,
                titulo,
                descripcion,
                duracionMinutos,
                categoria,
                streamer,
                drm,
                Collections.unmodifiableList(subtitulos)
        );
    }

    public static class Video implements Cloneable {
        private final String id;
        private final String titulo;
        private final String descripcion;
        private final int duracionMinutos;
        private final String categoria;
        private final VideoStreamer streamer;
        private final DRMProtection drm;
        private final List<String> subtitulos;

        private Video(String id, String titulo, String descripcion, int duracionMinutos,
                       String categoria, VideoStreamer streamer, DRMProtection drm,
                       List<String> subtitulos) {
            this.id = id;
            this.titulo = titulo;
            this.descripcion = descripcion;
            this.duracionMinutos = duracionMinutos;
            this.categoria = categoria;
            this.streamer = streamer;
            this.drm = drm;
            this.subtitulos = subtitulos;
        }

        public String getId() { return id; }
        public String getTitulo() { return titulo; }
        public String getDescripcion() { return descripcion; }
        public int getDuracionMinutos() { return duracionMinutos; }
        public String getCategoria() { return categoria; }
        public VideoStreamer getStreamer() { return streamer; }
        public DRMProtection getDrm() { return drm; }
        public List<String> getSubtitulos() { return subtitulos; }

        public String reproducir() {
            return "Reproduciendo '" + titulo + "' en calidad " + streamer.streamQuality()
                    + " | Proteccion: " + drm.applyDRM();
        }

        @Override
        public Video clone() throws CloneNotSupportedException {
            return (Video) super.clone();
        }

        @Override
        public String toString() {
            return "Video{" +
                    "id='" + id + '\'' +
                    ", titulo='" + titulo + '\'' +
                    ", duracionMinutos=" + duracionMinutos +
                    ", categoria='" + categoria + '\'' +
                    ", subtitulos=" + subtitulos +
                    '}';
        }
    }
}