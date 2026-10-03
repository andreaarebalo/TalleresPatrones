package com.streaming.userservice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Map;

import org.junit.jupiter.api.Test;

import com.streaming.userservice.controller.StructuralPatternsController;
import com.streaming.userservice.composite.CatalogCollection;
import com.streaming.userservice.composite.CatalogVideo;
import com.streaming.userservice.decorator.AdvertisingDecorator;
import com.streaming.userservice.decorator.SubtitlesDecorator;
import com.streaming.userservice.factory.streaming.Mobile720pStreamer;

class StructuralPatternsTest {

    private final StructuralPatternsController controller = new StructuralPatternsController();

    @Test
    void adapterAdaptaElReproductorHeredado() {
        Map<String, String> response = controller.adapter();
        assertEquals("Adapter", response.get("patron"));
        assertTrue(response.get("resultado").contains("Reproductor heredado"));
        assertTrue(response.get("resultado").contains("720p HD"));
    }

    @Test
    void bridgeCombinaModoYPlataformaIndependientemente() {
        Map<String, String> response = controller.bridge("live", "mobile", "Concierto");
        assertEquals("Bridge", response.get("patron"));
        assertTrue(response.get("resultado").contains("En vivo: Concierto"));
        assertTrue(response.get("resultado").contains("móvil (720p)"));
    }

    @Test
    void compositeSumaDuracionesYVideosDeColeccionesAnidadas() {
        Map<String, Object> response = controller.composite();
        assertEquals(3, response.get("cantidadVideos"));
        assertEquals(367, response.get("duracionTotalMinutos"));
        assertTrue(((String) response.get("estructura")).contains("Películas"));
    }

    @Test
    void decoratorAgregaSubtitulosYAnunciosSinModificarElStreamer() {
        Map<String, String> response = controller.decorator("mobile", "es", true);
        assertEquals("Decorator", response.get("patron"));
        assertTrue(response.get("resultado").contains("720p HD"));
        assertTrue(response.get("resultado").contains("subtítulos: es"));
        assertTrue(response.get("resultado").contains("anuncios"));
    }

    @Test
    void compositePermiteGestionarArbolDeFormaUniforme() {
        CatalogCollection root = new CatalogCollection("Raíz");
        CatalogCollection nested = new CatalogCollection("Infantil");
        nested.add(new CatalogVideo("Corto", 12));
        root.add(nested);
        root.add(new CatalogVideo("Documental", 30));

        assertEquals(2, root.videoCount());
        assertEquals(42, root.durationMinutes());
        assertTrue(root.describe().contains("Corto"));
    }

    @Test
    void decoradoresSePuedenEnvolverEnCadena() {
        var streamer = new AdvertisingDecorator(
                new SubtitlesDecorator(new Mobile720pStreamer(), "en"));

        assertTrue(streamer.streamQuality().contains("720p HD"));
        assertTrue(streamer.streamQuality().contains("subtítulos: en"));
        assertTrue(streamer.streamQuality().contains("anuncios"));
    }
}
